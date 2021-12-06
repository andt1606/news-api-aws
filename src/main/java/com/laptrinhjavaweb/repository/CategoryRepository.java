package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    //để lấy data lên trong jpa thì phải bắt đầu = find, findOne = single, findBy = List<>
    CategoryEntity findOneByCode(String code);
}
