package com.wechatorder.demo.service.impl;

import com.wechatorder.demo.dto.OrderDTO;
import com.wechatorder.demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Chris on 2020/4/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PushMessageServiceImplTest {

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;
    @Test
    void orderStatus() throws Exception{
        OrderDTO orderDTO = orderService.findOne("1234567");
        pushMessageService.orderStatus(orderDTO);
    }
}