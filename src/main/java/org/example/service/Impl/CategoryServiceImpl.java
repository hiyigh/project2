package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.util.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategoryList(CategoryType categoryType) {
        return categoryRepository.getCategoryList(categoryType);
    }
}
