package com.xiaou.eduservice.service;

import com.xiaou.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-22
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoById(String courseId);
}
