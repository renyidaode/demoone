package com.newyhree.demoone.service.impl;

import com.newyhree.demoone.dataObject.ProductCategory;
import com.sun.scenario.effect.impl.prism.PrDrawable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryImplTest {

    @Autowired
    private CategoryImpl category;

    @Test
    public void findOne() throws Exception{
        ProductCategory productCategory = category.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception{
        List<ProductCategory> productCategory = category.findAll();
        Assert.assertNotEquals(0, productCategory.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception{
        List<ProductCategory> productCategories = category.findByCategoryTypeIn(Arrays.asList(1,2));

        Assert.assertNotEquals(0, productCategories.size());
    }

    @Test
    public void save() throws Exception{
        ProductCategory productCategory = new ProductCategory("ceshi", 5);

        ProductCategory result = category.save(productCategory);

        Assert.assertNotNull(result);
    }
}