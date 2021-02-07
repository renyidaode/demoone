package com.newyhree.demoone.repository;

import com.newyhree.demoone.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
