package com.wechatorder.demo.exception;

import com.wechatorder.demo.enums.ResultEnum;
import lombok.Data;

/**
 * 自定义的异常
 * Created by Chris on 2020/3/5.
 */
@Data
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum){
        //将resultEnum的信息传入
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
