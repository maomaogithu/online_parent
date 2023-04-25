package com.xiaou.eduservice.controller;


import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.EduVideo;
import com.xiaou.eduservice.service.EduVideoService;
import com.xiaou.eduservice.client.VodClient;
import com.xiaou.servicebase.exceptionHandler.xiaouException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xiaou
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/online/edu-video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    //注入vodClient
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addEduVideo")
    public R addEduVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    //删除小节，删除对应阿里云视频
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            R result = vodClient.removeMoreAlyVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new xiaouException(20001,"删除视频失败，熔断器...");
            }
        }
        //删除小节
        eduVideoService.removeById(id);
        return R.ok();
    }

    //修改小节

}

