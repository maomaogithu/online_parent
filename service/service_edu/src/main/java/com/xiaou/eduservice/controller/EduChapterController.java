package com.xiaou.eduservice.controller;


import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.EduChapter;
import com.xiaou.eduservice.entity.chapter.ChapterVo;
import com.xiaou.eduservice.entity.chapter.VideoVo;
import com.xiaou.eduservice.entity.vo.CourseInfo;
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

    @Autowired


    //课程大纲列表 根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
       List<ChapterVo> videoVoList =  chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",videoVoList);
    }

    //添加章节接口
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.ok();
    }
    //根据章节id进行查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    //修改章节信息
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }
    //删除章节接口
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean result = chapterService.deleteChapter(chapterId);
        if(result){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

