package com.wechatorder.demo.converter;

import com.wechatorder.demo.dataobject.OrderMaster;
import com.wechatorder.demo.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chris on 2020/3/6.
 */
public class OrderMaster2OrderDTOConverter {
    /**
     * 单个orderMaster转换成orderDTO
     * @param orderMaster
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * 多个orderMaster转换成orderDTO
     * @param orderMasterList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList = orderMasterList.stream()
                .map(e->convert(e))
                .collect(Collectors.toList());
        return orderDTOList;
    }
}
