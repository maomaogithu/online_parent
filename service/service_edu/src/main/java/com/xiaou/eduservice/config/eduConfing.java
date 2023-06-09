package com.xiaou.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/13
 **/
@Configuration
@MapperScan("com.xiaou.eduservice.mapper")
public class eduConfing {
/**
 * 逻辑删除的插件
 */
@Bean
public ISqlInjector iSqlInjector(){
    return new LogicSqlInjector();
      }


/**
 * 分页插件
 */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    /**
     * 乐观锁插件
     *
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
}
