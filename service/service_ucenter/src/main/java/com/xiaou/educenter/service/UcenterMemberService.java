package com.xiaou.educenter.service;

import com.xiaou.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-29
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);
}
