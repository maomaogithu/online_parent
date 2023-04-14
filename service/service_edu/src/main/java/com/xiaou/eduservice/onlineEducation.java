package com.xiaou.eduservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
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
public class onlineEducation {
    public static void main(String[] args) {
        SpringApplication.run(onlineEducation.class,args);
    }
}
