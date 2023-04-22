package com.xiaoyu.code.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/21
 *
 * 监听类
 **/
public class excelListener extends AnalysisEventListener<excelData> {

    //一行一行的读取内容
    @Override
    public void invoke(excelData excelData, AnalysisContext analysisContext) {

    }

    //读取表头内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
