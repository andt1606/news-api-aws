package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getOneCategory(Long id);

    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO);
    void delete (long[] ids);
    void delete(Long id);

    List<NewDTO> getNews(Long id);
}
