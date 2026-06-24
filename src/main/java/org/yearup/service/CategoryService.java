package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    public List<Category> getById(Long id)
    {
       return categoryRepository.findByCategoryId(id);
    }

    public Category create(Category category)
    {
        return categoryRepository.save(category);
    }

    public Category update(int categoryId, Category updatedCategory)
    {
        Category existing = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
        existing.setName(updatedCategory.getName());
        existing.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(existing);
    }

    public void delete(int categoryId)
    {
        categoryRepository.deleteByCategoryId(categoryId);
    }
}
