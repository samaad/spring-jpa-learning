package org.bee.jpalearngin.BookCatelog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.bee.jpalearngin.BookCatelog.dto.Mapper;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.CategoryDTO;
import org.bee.jpalearngin.BookCatelog.dto.response.CategoryResponseDTO;
import org.bee.jpalearngin.BookCatelog.entitt.Category;
import org.bee.jpalearngin.BookCatelog.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
            new IllegalArgumentException("could not find category with id: " + categoryId));
    }

    @Override public CategoryResponseDTO addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return Mapper.categoryToCategoryResponseDTO(category);
    }

    @Override public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = getCategory(categoryId);
        return Mapper.categoryToCategoryResponseDTO(category);
    }

    @Override public List<CategoryResponseDTO> getCategories() {
        List<Category> categories = new ArrayList<>(categoryRepository.findAll());
        return Mapper.categoriesToCategoryResponseDTOs(categories);
    }

    @Override public CategoryResponseDTO deleteCategory(Long categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return Mapper.categoryToCategoryResponseDTO(category);
    }

    @Transactional
    @Override public CategoryResponseDTO editCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category categoryToEdit = getCategory(categoryId);
        categoryToEdit.setName(categoryDTO.getName());
        return Mapper.categoryToCategoryResponseDTO(categoryToEdit);
    }
}
