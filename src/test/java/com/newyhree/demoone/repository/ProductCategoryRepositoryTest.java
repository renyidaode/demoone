package com.newyhree.demoone.repository;

import com.newyhree.demoone.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findById(1).get();

        System.out.println(productCategory.toString());

    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("福利", 3);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);

    }

    @Test
    public void updateTest(){
        ProductCategory productCategory1 = repository.findById(1).get();
        productCategory1.setCategoryType(2);
        productCategory1.setCategoryName("王五");
        repository.save(productCategory1);

    }

    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list = Arrays.asList(2, 1);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }


}