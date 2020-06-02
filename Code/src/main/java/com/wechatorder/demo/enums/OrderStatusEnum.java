package com.wechatorder.demo.enums;

/**
 * Created by Chris on 2020/3/4.
 */
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "NewOrder"),
    FINISHED(1, "Finished"),
    CANCEL(2, "Cancel"),
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
