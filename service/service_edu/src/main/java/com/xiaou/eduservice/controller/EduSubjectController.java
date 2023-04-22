package com.xiaou.eduservice.controller;


import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.sub.level1;
import com.xiaou.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xiaou
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/online/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //获取上传的文件，读取其中的内容
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }
    /**
     * 课程分类树形列表
     */
    @GetMapping("getAllSublist")
    public R getAllSubList(){
        //获取所有记录 泛型中填一级
       List<level1> list = eduSubjectService.getAllRecords();
        return R.ok().data("list",list);
    }
}

