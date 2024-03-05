package org.example.model.entity.shop;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageFile {
    private int imgFileId;
    private int itemId;
    private String imgFilepath;
    private String imgFilename;
    @Builder
    public ImageFile(int itemId, String imgFilepath, String imgFilename) {
        this.itemId = itemId;
        this.imgFilepath = imgFilepath;
        this.imgFilename = imgFilename;
    }
}
