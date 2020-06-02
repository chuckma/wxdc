package com.wechatorder.demo.utils;

import com.wechatorder.demo.enums.CodeEnum;

/**
 * Created by Chris on 2020/3/5.
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        //getEnumConstants():返回此枚举类的元素，如果此Class对象不表示枚举类型，则返回null
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
