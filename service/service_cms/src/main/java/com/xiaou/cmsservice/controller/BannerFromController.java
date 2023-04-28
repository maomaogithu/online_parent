package com.xiaou.cmsservice.controller;

import com.xiaou.cmsservice.entity.CrmBanner;
import com.xiaou.cmsservice.service.CrmBannerService;
import com.xiaou.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/26
 **/
@RestController
@CrossOrigin
@Api(value = "网站首页Banner列表")
@RequestMapping("/educms/bannerfrom")
public class BannerFromController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        List<CrmBanner> list = bannerService.selectAllbanner();
        return R.ok().data("bannerList", list);
    }

}
