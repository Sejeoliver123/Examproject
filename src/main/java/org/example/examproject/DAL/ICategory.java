package org.example.examproject.DAL;

import org.example.examproject.BE.Category;

import java.util.List;

public interface ICategory {
    List<Category> getAllCategory() throws Exception;

    Category createCategory(Category category) throws Exception;

    void updateCategory(Category category) throws Exception;

    void deleteCategory(Category category) throws Exception;
}
