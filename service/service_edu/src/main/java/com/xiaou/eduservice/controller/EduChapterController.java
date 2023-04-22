package com.xiaou.eduservice.controller;


import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.chapter.ChapterVo;
import com.xiaou.eduservice.entity.chapter.VideoVo;
import com.xiaou.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/online/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;


    //课程大纲列表 根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
       List<ChapterVo> videoVoList =  chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",videoVoList);
    }
}

