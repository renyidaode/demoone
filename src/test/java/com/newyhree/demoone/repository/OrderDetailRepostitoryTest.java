package com.newyhree.demoone.repository;

import com.newyhree.demoone.dataObject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepostitoryTest {

    @Autowired
    private OrderDetailRepostitory repostitory;

    @Test
    public void findByOrderId() {
        List<OR>
    }

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("123");
        orderDetail.setProductIcon("1111");
        orderDetail.setProductIcon("http://www.1234.jpg");
        orderDetail.setProductId("123");
        orderDetail.setProductName("拉拉");
        orderDetail.setProductPrice(new BigDecimal(18.3));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repostitory.save(orderDetail);
        Assert.assertNotNull(result);
    }
}