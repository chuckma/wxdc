package com.wechatorder.demo.controller;

import com.wechatorder.demo.dto.OrderDTO;
import com.wechatorder.demo.enums.ResultEnum;
import com.wechatorder.demo.exception.SellException;
import com.wechatorder.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端功能代码
 * Created by Chris on 2020/4/10.
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 打印订单列表
     * 模板视图：
     *   （1）SpringBoot：使用ModelMap
     *   （）SpringMVC：使用ModelAndView  （这两个等价，两者的方法差不多相同）
     * @param page：默认从第一页开始
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size,
                       ModelMap map){
        //获取参数
        PageRequest request = PageRequest.of(page - 1, size);
        //根据参数获取对应页数的数据
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        //使用模板显示数据
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return "order/list";
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public String cancel(@RequestParam("orderId") String orderId,
                         ModelMap map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return "common/error";
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getCode());
        map.put("url", "/sell/seller/order/list");
        return "common/success";
    }


    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("orderId") String orderId,
                         ModelMap map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);
        }catch(SellException e){
            log.error("【卖家端查询订单详情】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return "common/error";
        }
        map.put("orderDTO", orderDTO);
        return "order/detail";
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public String finished(@RequestParam("orderId") String orderId,
                           ModelMap map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return "common/error";
        }

        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return "common/success";
    }
}
