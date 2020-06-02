package com.wechatorder.demo.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wechatorder.demo.dataobject.OrderDetail;
import com.wechatorder.demo.dto.OrderDTO;
import com.wechatorder.demo.enums.ResultEnum;
import com.wechatorder.demo.exception.SellException;
import com.wechatorder.demo.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2020/3/6.
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        //将浏览器输入的数据传入，生成订单信息
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        //生成订单详情列表
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            //进行数据解析,TypeToken目标格式
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {}.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //存入订单详情列表
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
