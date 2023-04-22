package com.xiaou.eduservice.service;

import com.xiaou.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.eduservice.entity.vo.CourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-22
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfo courseInfo);

    CourseInfo getCrouseInfo(String crouseId);

    void updateCrouseInfo(CourseInfo courseInfo);
}
