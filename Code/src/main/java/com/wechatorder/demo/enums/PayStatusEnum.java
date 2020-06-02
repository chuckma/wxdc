package com.wechatorder.demo.enums;

/**
 * Created by Chris on 2020/3/4.
 */
public enum PayStatusEnum implements CodeEnum{
    WAIT(0, "Waitting for the payment"),
    SUCCESS(1, "Payment success"),
    ;

    private Integer code;
    private String  message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }
}
