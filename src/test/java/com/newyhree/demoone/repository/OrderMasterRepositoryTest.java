package com.newyhree.demoone.repository;

import com.newyhree.demoone.dataObject.OrderMaster;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;


    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("帅锅");
        orderMaster.setBuyerPhone("18249676548");
        orderMaster.setBuyerAddress("上海朝阳区");
        orderMaster.setBuyerOpenid("101200");
        orderMaster.setOrderAmount(new BigDecimal(5.6));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne(){
        OrderMaster orderMaster = repository.findById("123456").get();
        orderMaster.setBuyerName("靓仔");
        OrderMaster result = repository.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid(){
        PageRequest pageRequest = PageRequest.of(0, 1);

        Page<OrderMaster> result = repository.findByBuyerOpenid("101200", pageRequest);
        Assert.assertNotEquals(0,result.getSize());
        System.out.println(result.getTotalElements());
    }
}