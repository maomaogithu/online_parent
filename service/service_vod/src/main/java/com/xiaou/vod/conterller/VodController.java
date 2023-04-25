package com.xiaou.vod.conterller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.xiaou.commonutils.R;
import com.xiaou.servicebase.exceptionHandler.xiaouException;
import com.xiaou.vod.service.VodService;
import com.xiaou.vod.utils.ConstanVodtils;
import com.xiaou.vod.utils.InitVodCilent;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/25
 **/
@RestController
@RequestMapping("eduvod/video")
@CrossOrigin
@Api(value = "上传视频到阿里云接口")
public class VodController {

    @Autowired
    private VodService vodService;


    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file){
       String videoId =  vodService.uploadVideoAly(file);
        return R.ok().data("videoId",videoId); //返回视频id
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstanVodtils.ACCESS_KEY_ID, ConstanVodtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new xiaouException(20001,"删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }

}
