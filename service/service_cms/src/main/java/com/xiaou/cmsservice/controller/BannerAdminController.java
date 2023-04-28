package com.xiaou.cmsservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaou.cmsservice.entity.CrmBanner;
import com.xiaou.cmsservice.service.CrmBannerService;
import com.xiaou.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/26
 **/
@RestController
@CrossOrigin
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("pageBanner/{page}/{limit}")
    @ApiOperation(value = "分页查询banner的接口")
    public R pageBanner(@PathVariable long page, @PathVariable long limit){
        Page<CrmBanner> objectPage = new Page<>(page, limit);
        bannerService.page(objectPage,null);
        return R.ok().data("items",objectPage.getRecords()).data("total",objectPage.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public R save(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return R.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }
}
