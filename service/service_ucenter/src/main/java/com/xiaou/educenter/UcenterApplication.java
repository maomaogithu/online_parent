package com.xiaou.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/25
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaou"})
@EnableDiscoveryClient
@MapperScan("com.xiaou.educenter.mapper")
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }

}
