package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.shop.ImageFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ImageFileRepository {
    private final JdbcTemplate jdbcTemplate;
    public void saveFile(ImageFile itemFile) {
        String sql = "insert into ImageFile (item_id, filepath, filename) values (?,?,?)";
        jdbcTemplate.update(sql, itemFile.getItemId(), itemFile.getImgFilepath(), itemFile.getImgFilename());
    }
    public void removeFile(String filename) {
        String sql = "delete from ImageFile where filename like ?";
        jdbcTemplate.update(sql, filename);
    }
    public List<ImageFile> getItemFileById(int itemId) {
        String sql = "select * from ImageFile where item_id = ?";
        List<ImageFile> imageFiles = new ArrayList<>();
        imageFiles = jdbcTemplate.queryForList(sql, ImageFile.class, itemId);
        return imageFiles;
    }

}
