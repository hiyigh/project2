package org.example.model.entity.shop;

import lombok.Getter;

@Getter
public class ItemFile {
    private int img_file_id;
    private int item_id;
    private String img_filepath;
    private String img_filename;
    public ItemFile(int item_id, String img_filepath, String img_filename) {
        this.item_id = item_id;
        this.img_filepath = img_filepath;
        this.img_filename = img_filename;
    }

    public void updateFilePath(String filePath) {
        this.img_filepath = filePath;
    }
    public void updateFileName(String fileName) {
        this.img_filename = fileName;
    }
}
