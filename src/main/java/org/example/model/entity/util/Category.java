package org.example.model.entity.util;

public enum Category {
    BOOK("책"),
    ETC("기타");
    private final String value;
    Category(String value) {
        this.value = value;
    }
    public String getValue(){return this.value;}
}
