package com.newyhree.demoone.service;


import com.newyhree.demoone.dataObject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    ProductCategory getOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
