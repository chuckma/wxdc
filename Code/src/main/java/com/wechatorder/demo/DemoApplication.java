package com.wechatorder.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//使用redis缓存
@EnableCaching
//Mybatis测试：配置扫描路径，让测试类可以使用autowired
@MapperScan(basePackages = {"com.wechatorder.demo.dataobject.mapper"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
