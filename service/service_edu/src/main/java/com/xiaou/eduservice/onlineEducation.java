package com.xiaou.eduservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/13
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaou"})
@EnableDiscoveryClient //nacos注册
@EnableFeignClients //服务发现
public class onlineEducation {
    public static void main(String[] args) {
        SpringApplication.run(onlineEducation.class,args);
    }
}
