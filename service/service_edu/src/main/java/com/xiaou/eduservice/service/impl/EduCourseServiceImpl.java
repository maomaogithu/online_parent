package com.xiaou.eduservice.service.impl;

import com.xiaou.eduservice.entity.EduCourse;
import com.xiaou.eduservice.entity.EduCourseDescription;
import com.xiaou.eduservice.entity.vo.CourseInfo;
import com.xiaou.eduservice.mapper.EduCourseMapper;
import com.xiaou.eduservice.service.EduCourseDescriptionService;
import com.xiaou.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.servicebase.exceptionHandler.xiaouException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-22
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService descriptionService;


    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        //向课程表添加信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfo,course);
        int insert = baseMapper.insert(course);
        //判断
        if(insert == 0){
            throw new xiaouException(20001,"添加课程信息失败");
        }

        //获取添加后的课程id
        String cid = course.getId();
        //向课程简介表添加课程描述
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfo.getDescription());
        description.setId(cid);
        descriptionService.save(description);

        return cid;
    }

    @Override
    public CourseInfo getCrouseInfo(String crouseId) {
        //查询课程表
        EduCourse course = baseMapper.selectById(crouseId);
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(course,courseInfo);

        //查询课程信息描述表
        EduCourseDescription courseDescription = descriptionService.getById(crouseId);
        courseInfo.setDescription(courseDescription.getDescription());

        return courseInfo;
    }

    //修改课程信息
    @Override
    public void updateCrouseInfo(CourseInfo courseInfo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        baseMapper.updateById(eduCourse);

        //修改描述信息表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfo.getId());
        eduCourseDescription.setDescription(courseInfo.getDescription());
        descriptionService.updateById(eduCourseDescription);


    }
}
