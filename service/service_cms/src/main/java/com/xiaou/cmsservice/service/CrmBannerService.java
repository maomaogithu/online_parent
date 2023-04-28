package com.xiaou.cmsservice.service;

import com.xiaou.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-26
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllbanner();
}
