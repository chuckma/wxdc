package com.wechatorder.demo.service.impl;

import com.wechatorder.demo.dataobject.OrderDetail;
import com.wechatorder.demo.dto.OrderDTO;
import com.wechatorder.demo.enums.OrderStatusEnum;
import com.wechatorder.demo.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用GoTo -> Test 创建新的测试类，可以直接在上面更新原有测试类的方法
 * Created by Chris on 2020/3/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "154204";

    private final String ORDER_ID = "1583419047531652";

    @Test
    void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("FYJ");
        orderDTO.setBuyerAddress("HN");
        orderDTO.setBuyerPhone("135482");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1234567");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【根据orderId查询订单详情】 result = {}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    void findListAll() {
        //页码 + 数据条数
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
//        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表", orderDTOPage.getTotalElements() > 0);
    }

    @Test
    void findList() {
        PageRequest request = PageRequest.of(1,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        System.out.println("***************" + orderDTOPage.getTotalElements());
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    void cancel() {
        //先查找一个订单
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        //进行取消操作
        OrderDTO result = orderService.cancel(orderDTO);
        //断言
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    void finish() {
        //先查找一个订单
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        //进行完成操作
        OrderDTO result = orderService.finish(orderDTO);
        //断言
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    void paid() {
        //先查找一个订单
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        //进行支付操作
        OrderDTO result = orderService.paid(orderDTO);
        //断言
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}