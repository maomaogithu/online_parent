package com.xiaou.eduservice.controller;


import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.EduTeacher;
import com.xiaou.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xiaou
 * @since 2023-04-13
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@Api(value = "教师管理接口")
public class EduTeacherController {
    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;
    //调用service中的方法 rest风格
    @GetMapping("queryAll")
    @ApiOperation(value = "查询所有教师信息")
    public R queryAll(){
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return R.ok().data("item",teacherList);
    }




}

