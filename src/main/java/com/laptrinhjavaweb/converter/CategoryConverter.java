package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO convertToCategoryDTO(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO =modelMapper.map(categoryEntity,CategoryDTO.class);
        return categoryDTO;
    }

    public CategoryEntity convertToCategoryEntity(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity =modelMapper.map(categoryDTO,CategoryEntity.class);
        return categoryEntity;
    }

    public CategoryEntity convertToCategoryEntity(CategoryDTO categoryDTO, CategoryEntity categoryEntity){
        categoryEntity =modelMapper.map(categoryDTO,CategoryEntity.class);
        return categoryEntity;
    }
}
