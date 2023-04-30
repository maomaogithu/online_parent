package com.xiaou.msmservice.controller;

import com.xiaou.commonutils.R;
import com.xiaou.msmservice.service.MsmService;
import com.xiaou.msmservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * TOOD
 *
 * @Description 发送阿里云短信
 * @Author xiaou
 * @Date$ 2023/4/28
 **/
@RestController
@CrossOrigin
@RequestMapping("/edumsm/sendmsm")
@Api(value = "短信发送接口")
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //1.先从redis中取验证码 如果取到直接返回
        String redisCode = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(redisCode)){
            return R.ok();
        }
        //调用工具类生成随机值发送给阿里云
        String code = RandomUtil.getFourBitRandom();
        HashMap<String, Object> parmas = new HashMap<>();
        parmas.put("code",code);

        boolean sendResult = msmService.sendMsm(parmas,phone);
        if(sendResult){
            //发送成功，存redis 并且设置过期时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);//过期时间5分钟
            return R.ok();
        }else {
            return R.error().message("短信发送失败！");
        }
    }
}
