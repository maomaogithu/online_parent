package com.xiaou.eduservice.client;

import com.xiaou.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/26
 **/
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeMoreAlyVideo(String id) {
        return R.ok().message("删除视频出错！");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.ok().message("删除多个视频出错！");
    }
}
