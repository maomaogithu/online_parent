package com.xiaou.eduservice.entity.sub;

import lombok.Data;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/21
 *
 * 一级分类
 **/
@Data
public class level1 {

    private  String id;

    private String title;

    private List<level2> children;
}
