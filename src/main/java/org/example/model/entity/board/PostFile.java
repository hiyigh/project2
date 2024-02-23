package org.example.model.entity.board;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostFile {
    private int img_id;
    private int post_id;
    private String img_file_path;
    private String img_file_name;

    @Builder
    public PostFile(int post_id, String img_file_name, String img_file_path) {
        this.post_id=post_id;
        this.img_file_path = img_file_path;
        this.img_file_name = img_file_name;
    }
    public void updateFilePath(String filePath) {
        this.img_file_path = filePath;
    }
    public void updateFileName(String update_file_name) {
        this.img_file_name = update_file_name;
    }
}
