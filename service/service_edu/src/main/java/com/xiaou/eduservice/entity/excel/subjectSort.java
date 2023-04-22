package com.xiaou.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/21
 *
 * 和excel对应的实体类
 **/
@Data
public class subjectSort {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;

}
