package com.wechatorder.demo.repository;

import com.wechatorder.demo.dataobject.OrderMaster;
import com.wechatorder.demo.enums.OrderStatusEnum;
import com.wechatorder.demo.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Chris on 2020/3/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;
    private final String OPENID = "123";

    @Test
    public void saveTest(){
        Date date = new Date();
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("chris");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("mooc");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(date);
        orderMaster.setUpdateTime(date);

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception{
        //错误写法，该方法属于旧版本：PageRequest request = new PageRequest(1, 3);
        PageRequest request = PageRequest.of(0,3);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
        System.out.println("*****************\n" +
                result.getTotalElements()  + "\n" +
                result.toString() + "\n" +
                result.getTotalPages());
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}