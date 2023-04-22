package com.xiaoyu.code.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/21
 *
 * easyExcel的实体类
 **/
@Data
public class excelData {
    //设置表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;


}
