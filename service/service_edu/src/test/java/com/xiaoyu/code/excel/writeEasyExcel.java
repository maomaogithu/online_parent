package com.xiaoyu.code.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/21
 *
 * 写操作
 **/
public class writeEasyExcel {

    public static void main(String[] args) {
        //1.设置写入文件的路径和名称
        //String filePath = "E:\\write.xlsx";

        //2.调用easyExcel里面的方法实现写操作
        //EasyExcel.write(filePath,excelData.class).sheet("学生列表").doWrite(getData());

        //实现读操作
        String filePath = "E:\\write.xlsx";
        EasyExcel.read(filePath,excelData.class,new excelListener()).sheet().doRead();



    }
    private static List<excelData> getData(){
        ArrayList<excelData> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            excelData data = new excelData();
            data.setSno(i);
            data.setSname("nanx"+i);
            list.add(data);
        }
        return list;
    }
}
