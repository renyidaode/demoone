package com.newyhree.demoone.service.impl;

import com.newyhree.demoone.dataObject.ProductCategory;
import com.newyhree.demoone.repository.ProductCategoryRepository;
import com.newyhree.demoone.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository categoryService;


    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryService.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryService.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryService.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return categoryService.save(productCategory);
    }
}
