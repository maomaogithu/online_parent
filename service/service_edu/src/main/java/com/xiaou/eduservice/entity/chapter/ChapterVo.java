package com.xiaou.eduservice.entity.chapter;

import lombok.Data;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/22
 *
 * 章节实体
 **/
@Data
public class ChapterVo {

    private String id;

    private String title;

    //小节
    private List<VideoVo> children;

}
