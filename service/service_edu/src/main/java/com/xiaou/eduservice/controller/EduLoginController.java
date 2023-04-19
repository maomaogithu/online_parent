package com.xiaou.eduservice.controller;

import com.xiaou.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/17
 **/
@RestController
@RequestMapping("/online/user")
@CrossOrigin//解决跨域问题的注解
public class EduLoginController {
    //1.登录方法login
    @PostMapping("login")
    @ApiOperation(value = "模拟登录返回token")
    public R login(){
        return R.ok().data("token","admin");
    }

    //返回信息的方法
    @GetMapping("logInfo")
    public R returnInfo(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
