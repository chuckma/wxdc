package com.wechatorder.demo.utils;

import java.util.Random;

/**
 * 生成随机数：用于orderId等随机值
 * Created by Chris on 2020/3/5.
 */
public class KeyUtil {
    /**
     * 生成唯一的主键：
     * synchronized：同步锁，防止出现相同的主键
     * 格式：时间 + 随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
