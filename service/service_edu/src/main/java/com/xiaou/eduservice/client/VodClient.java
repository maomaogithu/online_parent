package com.xiaou.eduservice.client;

import com.xiaou.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/25
 **/
@Component
@FeignClient("service-vod") //你所调用的服务名称 这里调用的service-vod
public interface VodClient {
    //定义调用的路径
    @DeleteMapping("eduvod/video/removeMoreAlyVideo/{id}")
    public R removeMoreAlyVideo(@PathVariable("id")String id);

    //定义调用删除多个视频的方法
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
