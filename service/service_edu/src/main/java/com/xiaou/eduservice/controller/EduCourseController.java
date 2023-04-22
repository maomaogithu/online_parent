package com.xiaou.eduservice.controller;


import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.vo.CourseInfo;
import com.xiaou.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xiaou
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/online/edu-course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息
    @PostMapping("addCrouseInfo")
    public R addCourseInfo(@RequestBody CourseInfo courseInfo){
        String id = eduCourseService.saveCourseInfo(courseInfo);
        return R.ok().data("courseId",id);
    }

    //根据课程查询课程基本信息
    @GetMapping("getCourseInfo/{crouseId}")
    public R getCourseInfo(@PathVariable String crouseId){
        CourseInfo courseInfo = eduCourseService.getCrouseInfo(crouseId);
        return R.ok().data("courseInfo",courseInfo);
    }

    //修改课程信息
    @PostMapping("updateCrouseInfo")
    public R updateCrouseInfo(@RequestBody CourseInfo courseInfo){

        eduCourseService.updateCrouseInfo(courseInfo);
        return R.ok();

    }
}

