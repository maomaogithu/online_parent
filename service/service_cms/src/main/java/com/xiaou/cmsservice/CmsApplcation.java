package com.xiaou.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@MapperScan("com.xiaou.cmsservice.mapper")
public class CmsApplcation {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplcation.class,args);
    }
}
