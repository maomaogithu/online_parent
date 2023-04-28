package com.xiaou.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.EduCourse;
import com.xiaou.eduservice.entity.EduTeacher;
import com.xiaou.eduservice.service.EduCourseService;
import com.xiaou.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/27
 *
 * 展示热门数据的接口
 **/
@RestController
@RequestMapping("educms/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("queryCrouse")
    public R queryCrouse(){
        //查询前8的热门课程和
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("id");
        courseWrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(courseWrapper);

        //查询前4位教师
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherWrapper);
        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
