package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.shop.ItemDto;
import org.example.model.dto.shop.OrderDto;
import org.example.model.entity.User;
import org.example.model.entity.shop.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    public void save(User user) {
        String sql = "insert into Users (user_name, user_password, user_img, user_role) " +
                "values(?,?,?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getPassword(),
                user.getUserImage(), user.getRole());
    }
    public void delete(int user_id) {
        String sql = "delete from Users where user_id = ?";
        jdbcTemplate.update(sql, user_id);
    }
    public void edit(User user) {
        String sql = "update Users set user_name = ? , user_password = ? , user_img = ? where user_id = ? ";
        jdbcTemplate.update(sql, user.getName(), user.getPassword(),
                user.getUserImage(), user.getUserId());

    }
    public User getUserById(int userId) {
        String sql = "select * from Users where user_id = ?";
        return jdbcTemplate.queryForObject(sql, User.class, userId);
    }
    public User getUserByUserName(String userName){
        String sql ="select * from Users where user_name like ?";
        return jdbcTemplate.queryForObject(sql, User.class, userName);
    }
    public User getUserByEmail(String email) {
        String sql ="select * from Users where user_email like ?";
        return jdbcTemplate.queryForObject(sql, User.class, email);
    }
    public Map<String, Object> getUserPostList(int userId) {
        String sql = "select post_id, post_title from Posts where user_id = ?";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId);
        return mapping(result);
    }
    public List<Comment> getUserCommentList(int userId) {
        String sql = "select * from Comments where comment_writer = ?";
        List<Comment> result = jdbcTemplate.queryForList(sql, Comment.class, userId);
        return result;
    }
    public Map<String, Object> getUserBasketList(int userId) {
        String sql = "select i.*, f.item_filepath as file_path " +
                "from Basket b " +
                "join Items i on b.item_id = i.item_id " +
                "left join ItemImageFile f on i.item_id = f.item_id " +
                "where b.user_id = ? ";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userId);
        Map<String, Object> result = mappingItemList(rows, 0);
        return result;
    }

    public int getUserIdByEmail(String email) {
        String sql = "select user_id from Users where user_email like ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class ,email);
        if (result == null) result = 0;
        return (int) result;
    }
    public Map<String, Object> getUserPurchaseList(int userId) {
        // order , item, img
        String sql = "select o.*, i.*, f.item_filepath as file_path " +
                "from Orders o" +
                "left join Items i on o.item_id = i.item_id " +
                "left join ItemImageFile f on o.item_id = f.item_id " +
                "where o.item_id = ? ";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userId);
        Map<String, Object> result = mappingItemList(rows, 1);
        return result;
    }
    private Map<String, Object> mappingItemList(List<Map<String, Object>> rows, int type) {
        Map<String, Object> result = new HashMap<>();
        List<String> filePathList =  new ArrayList<>();
        List<ItemDto.Response> itemList = new ArrayList<>();
        List<OrderDto> orderList = new ArrayList<>();
        OrderDto orderDto = new OrderDto();
        ItemDto.Response itemDto = new ItemDto.Response();
        if (type == 0) {
            for (int i = 0; i < rows.size(); ++i) {
                if (itemDto == null) {
                    itemDto.setItemId((int)rows.get(i).get("id"));
                    itemDto.setSeller((String)rows.get(i).get("seller"));
                    itemDto.setTitle((String) rows.get(i).get("title"));
                    itemDto.setExplain((String) rows.get(i).get("explain"));
                    itemDto.setPrice((int) rows.get(i).get("price"));
                    itemDto.setHits((int) rows.get(i).get("hits"));
                    itemDto.setStock((int) rows.get(i).get("stock"));
                    itemDto.setLikes((int)rows.get(i).get("likes"));
                    itemDto.setDiscount((int) rows.get(i).get("discount"));
                    itemList.add(itemDto);
                    itemDto = null;
                }
                filePathList.add((String)rows.get(i).get("file_path"));
            }
            result.put("filepathList", filePathList);
            result.put("itemList", itemList);
            return result;
        } else {
            for (int i = 0; i < rows.size(); ++i) {
                if (itemDto == null) {
                    itemDto.setItemId((int)rows.get(i).get("id"));
                    itemDto.setSeller((String)rows.get(i).get("seller"));
                    itemDto.setTitle((String) rows.get(i).get("title"));
                    itemDto.setExplain((String) rows.get(i).get("explain"));
                    itemDto.setPrice((int) rows.get(i).get("price"));
                    itemDto.setHits((int) rows.get(i).get("hits"));
                    itemDto.setStock((int) rows.get(i).get("stock"));
                    itemDto.setLikes((int)rows.get(i).get("likes"));
                    itemDto.setDiscount((int) rows.get(i).get("discount"));
                    itemList.add(itemDto);

                    itemDto = null;
                } else if (orderDto == null) {
                    orderList.add(orderDto);

                    orderDto = null;
                }
                filePathList.add((String)rows.get(i).get("file_path"));
            }
            result.put("filepathList", filePathList);
            result.put("orderList", orderList);
            result.put("itemList", itemList);
            return result;
        }
    }
    private Map<String, Object> mapping(List<Map<String, Object>> rows) {
        Map<String, Object> result = new HashMap<>();
        List<Integer> postIdList = new ArrayList<>();
        List<String> postTitleList = new ArrayList<>();
        for (int i = 0; i < rows.size(); ++i) {
            postIdList.add((int) rows.get(i).get("post_id"));
            postTitleList.add((String)rows.get(i).get("post_title"));
        }
        result.put("postIdList", postIdList);
        result.put("postTitleList", postTitleList);
        return result;
    }


    public void setBasket(int itemId, String userName) {
        String sql = "insert into Basket (userName, itemId) values (?,?)";
        jdbcTemplate.update(sql, userName, itemId);
    }
}
