package com.xiaou.servicebase.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/14
 **/
@Data
@AllArgsConstructor//生成有参数构造方法
@NoArgsConstructor //生成无参构造方法
public class xiaouException extends RuntimeException{
    //状态码
    private Integer code;
    //异常信息
    private String msg;

    public String toString() {
        return "xiaouException{" +
        "message=" + this.getMessage() +
        ", code=" + code +
        '}';
    }

}
