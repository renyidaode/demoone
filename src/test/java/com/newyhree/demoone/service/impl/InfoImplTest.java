package com.newyhree.demoone.service.impl;

import com.newyhree.demoone.dataObject.ProductInfo;
import com.newyhree.demoone.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoImplTest {

    @Autowired
    private InfoImpl info;

    @org.junit.Test
    public void findOne() throws Exception{
        ProductInfo productInfo = info.findOne("1");
        Assert.assertEquals("1", productInfo.getProductId());
    }

    @org.junit.Test
    public void findUpAll() throws Exception{
        List<ProductInfo> productInfoList = info.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @org.junit.Test
    public void findAll() throws Exception{
        PageRequest request = PageRequest.of(0, 10);
        Page<ProductInfo> productInfos = info.findAll(request);
        System.out.println(productInfos.getTotalElements());
    }

    @Test
    public void save() throws Exception{
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("13");
        productInfo.setProductName("好吃");
        productInfo.setProductPrice(new BigDecimal(3.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃");
        productInfo.setProductIcon("zcs.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);
        ProductInfo result = info.save(productInfo);
        Assert.assertNotNull(result);
    }
}