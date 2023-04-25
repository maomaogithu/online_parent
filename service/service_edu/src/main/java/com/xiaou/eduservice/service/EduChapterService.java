package com.xiaou.eduservice.service;

import com.xiaou.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.eduservice.entity.chapter.ChapterVo;

import java.util.ArrayList;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-22
 */
public interface EduChapterService extends IService<EduChapter> {

    ArrayList<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCrouseId(String courseId);
}
