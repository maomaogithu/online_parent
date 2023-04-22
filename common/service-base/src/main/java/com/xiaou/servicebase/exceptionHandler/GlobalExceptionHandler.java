package com.xiaou.servicebase.exceptionHandler;


import com.xiaou.commonutils.R;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TOOD
 *
 * @Description
 * @Author xiaoyu
 * @Date$ 2023/4/14
 * 异常处理类
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理.....");
    }
    /**
     * 自定义异常类
     */
    @ExceptionHandler(xiaouException.class)
    @ResponseBody
    public R error(xiaouException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
