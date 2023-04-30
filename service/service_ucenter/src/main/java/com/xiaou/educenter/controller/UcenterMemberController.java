package com.xiaou.educenter.controller;


import com.xiaou.commonutils.JwtUtils;
import com.xiaou.commonutils.R;
import com.xiaou.educenter.entity.UcenterMember;
import com.xiaou.educenter.entity.vo.RegisterVo;
import com.xiaou.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xiaou
 * @since 2023-04-29
 */
@RestController
@RequestMapping("/educenter/ucenter-member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R login(@RequestBody UcenterMember ucenterMember){
        //调用service中的方法进行登录 返回token
        String token = memberService.login(ucenterMember);
        return R.ok().data("token",token);
    }
    //注册

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
}

