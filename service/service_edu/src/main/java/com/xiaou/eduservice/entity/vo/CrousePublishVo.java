package com.xiaou.eduservice.entity.vo;

import lombok.Data;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/24
 *
 * 用于课程发布所需信息的实体类
 **/
@Data
public class CrousePublishVo {

    private String id;

    //标题
    private String title;

    private String cover;

    private Integer lessonNum;

    private String sbujectLevlOne;

    private String sbujectLevlTwo;

    private String price;

    private String teacherName;


}
