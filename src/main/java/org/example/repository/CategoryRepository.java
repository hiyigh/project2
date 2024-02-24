package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.checkerframework.common.util.report.qual.ReportUnqualified;
import org.example.model.entity.util.Category;
import org.example.model.enums.CategoryType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    public List<Category> getCategoryList(CategoryType categoryType) {
        List<Category> result = new ArrayList<>();
        if (categoryType == CategoryType.POST) {
            String sql = "select * from CategoryList where category_part = 0";
            result = jdbcTemplate.queryForList(sql, Category.class);
        } else {
            String sql = "select * from CategoryList where category_part = 1";
            result = jdbcTemplate.queryForList(sql, Category.class);
        }
        return result;
    }
}
