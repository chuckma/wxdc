package com.wechatorder.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 日志测试
 * Created by Chris on 2020/3/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    @Test
    public void test(){
        String name = "chris Logger";
        String password = "123";

        log.error("====error====");
        log.warn("====warn====");
        log.info("====info====" + name + ": " + password);
        log.debug("====debug====");
    }
}
