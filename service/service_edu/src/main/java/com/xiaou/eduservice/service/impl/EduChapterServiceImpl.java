package com.xiaou.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.eduservice.entity.EduChapter;
import com.xiaou.eduservice.entity.EduVideo;
import com.xiaou.eduservice.entity.chapter.ChapterVo;
import com.xiaou.eduservice.entity.chapter.VideoVo;
import com.xiaou.eduservice.mapper.EduChapterMapper;
import com.xiaou.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.eduservice.service.EduVideoService;
import com.xiaou.servicebase.exceptionHandler.xiaouException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-22
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    //课程大纲具体实现类
    @Override
    public ArrayList<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询出所有的课程章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);

        //2.根据课程id查询出所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> videoList = videoService.list(wrapperVideo);

        //创建集合用于存放最终封装的数据
        ArrayList<ChapterVo> finalList = new ArrayList<>();

        //3.遍历进行封装
        for (int i = 0; i < chapterList.size(); i++) {
            EduChapter eduChapter = chapterList.get(i);
            //将这个eduChapter放入VO对象中
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            List<VideoVo> voList = new ArrayList<>();
            //遍历小节list集合
            for (int k = 0; k < videoList.size(); k++) {
                EduVideo eduVideo = videoList.get(k);
                //判断小节里面的chapterid和章节里面的id是否一致
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    //放到小节封装的集合中
                    voList.add(videoVo);
                }
            }
            chapterVo.setChildren(voList);
        }
        return finalList;
    }

    /**
     * 删除章节
     * @param chapterId
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据章节id去小节表里进行查询，如果有就不允许删除章节，没有删
        QueryWrapper<EduVideo> eduVideoWrapper = new QueryWrapper<>();
        eduVideoWrapper.eq("chapter_id",chapterId);

        int count = videoService.count(eduVideoWrapper);//查询有无记录

        if(count > 0){
            throw new xiaouException(20001,"小节信息不存在，无法删除！");

        }else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }

    @Override
    public void removeChapterByCrouseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }
}
