package com.wechatorder.demo.service;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * ======================  Redis缓存、分布式锁测试案例 =========================
 * 分布式锁
 * Created by Chris on 2020/4/17.
 */
@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 上锁
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key, String value){
        //如果可以设置key，返回true，说明已经被锁住了
        if(redisTemplate.opsForValue().setIfAbsent(key, value)){
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currentValue) && Long .parseLong(currentValue) < System.currentTimeMillis()){
            //获取上锁时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value){
        try{
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(currentValue)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }
        catch (Exception e ){
            log.error("【redis分布式锁】解锁异常， {}", e);
        }
    }
}
