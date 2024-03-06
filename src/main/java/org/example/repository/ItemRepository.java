package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.shop.Item;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final JdbcTemplate jdbcTemplate;
    public void saveItem(Item item) {
        String sql = "insert into Items (category_id, seller, title, explain, price, stock, hits, likes, discount)" +
                "values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, item.getCategoryId(),item.getSeller() ,item.getTitle(), item.getExplain(), item.getPrice(),
                item.getStock(), item.getHits(), item.getLikes(), item.getDiscount());
    }
    public void deleteItem(int itemId) {
        String sql = "delete from Items where id = ?";
        jdbcTemplate.update(sql, itemId);
    }

    public Item getItemById(int itemId) {
        String sql = "select * from Items where id = ?";
        Item item = new Item();
        try{
             item = jdbcTemplate.queryForObject(sql, Item.class, itemId);
        }catch(EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return item;
    }
    public void editItem(Item item) {
        String sql = "update Items set category_id = ? , title = ? , " +
                "explain = ? , price = ?, discount = ?";
        jdbcTemplate.update(sql, item.getCategoryId(), item.getTitle(), item.getExplain(), item.getPrice(), item.getDiscount());
    }

    public void setHits() {
        String sql = "update Items set hits = hits + 1";
        jdbcTemplate.update(sql);
    }

    public void setLikes(int likes) {
        String sql = "update Items set likes = ?";
        jdbcTemplate.update(sql, likes);
    }

    public void setStock(int stock) {
        String sql = "update Items set stock = ?";
        jdbcTemplate.update(sql, stock);
    }
}

