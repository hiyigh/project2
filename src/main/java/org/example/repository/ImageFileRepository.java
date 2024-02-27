package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.board.PostFile;
import org.example.model.entity.shop.Item;
import org.example.model.entity.shop.ItemFile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ImageFileRepository {
    private final JdbcTemplate jdbcTemplate;
    public void savePostFile(PostFile postFile) {
        String sql = "insert into BoardImageFile (post_id, img_file_path, img_file_name)" +
                "values (?,?,?)";
        jdbcTemplate.update(sql, postFile.getPost_id(), postFile.getImg_file_path(), postFile.getImg_file_name());
    }
    public void saveItemFile(ItemFile itemFile) {
        String sql = "insert into ItemImageFile (item_id, img_filepath, img_filename) " +
                "vales (?,?,?)";
        jdbcTemplate.update(sql, itemFile.getItem_id(), itemFile.getImg_filepath(), itemFile.getImg_filename());
    }

    public List<PostFile> getPostFileById(int postId) {
        String sql = null;
        List<PostFile> result = new ArrayList<>();
        if (postId == -1) {
            sql = "select * from BoardImageFile";
            result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PostFile.class));
            return result;
        } else {
            sql = "select * from BoardImageFile where post_id = ?";
            result = jdbcTemplate.query(sql, new Object[]{postId}, new BeanPropertyRowMapper<>(PostFile.class));
            return result;
        }
    }
    public List<ItemFile> getItemFileById(int itemId) {
        String sql = null;
        List<ItemFile> result = new ArrayList<>();
        if (itemId == -1) {
            sql = "select * from ItemImageFile";
            result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ItemFile.class));
            return result;
        } else {
            sql = "select * from ItemImageFile where item_id = ?";
            result = jdbcTemplate.query(sql, new Object[]{itemId}, new BeanPropertyRowMapper<>(ItemFile.class));
            return result;
        }
    }

    public void removeFile(String filename) {
        String sql = "delete from BoardImageFile where img_file_name like ?";
        jdbcTemplate.update(sql, filename);
    }
}
