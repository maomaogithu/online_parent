package com.xiaou.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.EduCourse;
import com.xiaou.eduservice.entity.EduTeacher;
import com.xiaou.eduservice.entity.vo.CourseInfo;
import com.xiaou.eduservice.entity.vo.CrousePublishVo;
import com.xiaou.eduservice.entity.vo.queryTeacher;
import com.xiaou.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    //根据课程id查询课程确认的信息
    @GetMapping("getCrousePublish/{crouseId}")
    public R getCrousePublish(@PathVariable String crouseId){
        CrousePublishVo crousePublishVo = eduCourseService.getCrousePublish(crouseId);
        return R.ok().data("crousePublishVo",crousePublishVo);
    }

    //课程最终发布 修改数据库中的字段
    @PostMapping("publihCourse/{courseId}")
    public R publihCourse(@PathVariable String courseId){
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");
        //调用修改的方法
        eduCourseService.updateById(course);
        return R.ok();
    }

    //课程列表数据
    @ApiOperation(value = "条件分页查询课程信息列表")
    @PostMapping("getCrouseList/{current}/{limit}")
    public R getCrouseList(@PathVariable long current,
                           @PathVariable long limit, @RequestBody(required = false) EduCourse course){
        //1.创建page对象
        Page<EduCourse> page = new Page<>(current,limit);
        //2.构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //3.获取vo实体对象
        String name = course.getTitle();//名称
        String status = course.getStatus();//状态

        //4.判断
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        eduCourseService.page(page,wrapper);

        //3.获取记录总数
        long total = page.getTotal();
        //4获取分页查询的数据
        List<EduCourse> records = page.getRecords();

        HashMap map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }
    //删除课程信息
    @DeleteMapping("removeCourse/{courseId}")
    public R removeCourse(@PathVariable String courseId){
        eduCourseService.removeCrouse(courseId);
        return R.ok();
    }
}

