package com.laptrinhjavaweb.service.Impl;

import com.laptrinhjavaweb.converter.CategoryConverter;
import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private NewConverter newConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity item: entities){
            CategoryDTO categoryDTO = categoryConverter.convertToCategoryDTO(item);
            results.add(categoryDTO);
        }
        return results;
    }

    @Override
    public CategoryDTO getOneCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findOne(id);
        CategoryDTO categoryDTO = categoryConverter.convertToCategoryDTO(categoryEntity);
        return categoryDTO;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity = categoryConverter.convertToCategoryEntity(categoryDTO);
        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryConverter.convertToCategoryDTO(categoryEntity);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        CategoryEntity oldCategoryEntity = categoryRepository.findOne(categoryDTO.getId());
        oldCategoryEntity.setCode(categoryDTO.getCode());
        oldCategoryEntity.setName(categoryDTO.getName());
        oldCategoryEntity = categoryRepository.save(oldCategoryEntity);
        return categoryConverter.convertToCategoryDTO(oldCategoryEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long item: ids){
            categoryRepository.delete(item);
        }
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public List<NewDTO> getNews(Long id) {
        List<NewDTO> results = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAllByCategoryId(id);
        for (NewEntity item: entities){
            NewDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }
        return results;
    }


}
