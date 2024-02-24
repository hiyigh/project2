package org.example.service;

import org.example.model.entity.util.Category;
import org.example.model.enums.CategoryType;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList(CategoryType categoryType);
}
