package com.xiaou.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.commonutils.JwtUtils;
import com.xiaou.commonutils.MD5;
import com.xiaou.educenter.entity.UcenterMember;
import com.xiaou.educenter.entity.vo.RegisterVo;
import com.xiaou.educenter.mapper.UcenterMemberMapper;
import com.xiaou.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.servicebase.exceptionHandler.xiaouException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-29
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //登录
    @Override
    public String login(UcenterMember ucenterMember) {
        //获取手机号
        String mobile = ucenterMember.getMobile();
        //获取密码
        String password = ucenterMember.getPassword();
        //先对手机号和密码判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new xiaouException(20001,"登录失败！");
        }
        //查询数据库中是否存在此条记录
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember member = baseMapper.selectOne(queryWrapper);
        //如果返回的对象为空
        if(member ==null){
            throw new xiaouException(20001,"登录失败！");
        }
        //判断密码是否正确
        if(!member.getPassword().equals(MD5.encrypt(password))){
            throw new xiaouException(20001,"登录失败！");
        }

        //判断用户状态，是否禁用 true为禁用
        if(member.getIsDisabled()){
            throw new xiaouException(20001,"该用户已禁用，登录失败！");
        }
        //登录成功 传入所需参数 获得token
        String token = JwtUtils.getJwtToken(member.getId(),member.getNickname());

        return token;
    }

    /**
     * 注册
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册所需的数据
        String mobile = registerVo.getMobile();//手机号
        String password = registerVo.getPassword(); //密码
        String nickname = registerVo.getNickname();//用户名
        String code = registerVo.getCode(); //验证码

        //判断手机号是否为空
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)||StringUtils.isEmpty(nickname)){
            throw new xiaouException(20001,"注册失败！");
        }
        //判断手机号是否已经被注册
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(queryWrapper);

        if(count > 0){
            throw new xiaouException(20001,"注册失败！");
        }
        //判断redis中的验证码和所传的是否一致
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)){
            throw new xiaouException(20001,"注册失败！");
        }
        //注册
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setNickname(nickname);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        member.setIsDisabled(false);//不禁用

        //调用保存的方法
        baseMapper.insert(member);

    }
}
