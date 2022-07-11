package org.bee.jpalearngin.BookCatelog.service;

import java.util.List;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.CategoryDTO;
import org.bee.jpalearngin.BookCatelog.dto.response.CategoryResponseDTO;
import org.bee.jpalearngin.BookCatelog.entitt.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    public Category getCategory(Long categoryId);
    public CategoryResponseDTO addCategory(CategoryDTO categoryDTO);
    public CategoryResponseDTO getCategoryById(Long categoryId);
    public List<CategoryResponseDTO> getCategories();
    public CategoryResponseDTO deleteCategory(Long categoryId);
    public CategoryResponseDTO editCategory(Long categoryId, CategoryDTO categoryDTO);
}
