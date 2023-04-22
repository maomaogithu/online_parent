package com.xiaou.eduservice.linstener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.eduservice.entity.EduSubject;
import com.xiaou.eduservice.entity.excel.subjectSort;
import com.xiaou.eduservice.service.EduSubjectService;



import java.util.Map;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/21
 **/
public class SubjectListener extends AnalysisEventListener<subjectSort> {

    //监听器没有交给Spring进行管理 所以需要实现该service的有参和无参构造函数
    public EduSubjectService subjectService;

    public SubjectListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectListener() {

    }


    @Override
    public void invoke(subjectSort excelData, AnalysisContext analysisContext) {
        //判断excel中有无内容
        if(excelData == null){
            throw new RuntimeException("文件内容为空！");
        }

        //返回的对象
        EduSubject eduSubjectOne = this.exitSubjectOne(subjectService, excelData.getOneSubjectName());
        if(eduSubjectOne == null){
            eduSubjectOne = new EduSubject();
            eduSubjectOne.setParentId("0");
            eduSubjectOne.setTitle(excelData.getOneSubjectName());
            subjectService.save(eduSubjectOne);
        }
        //获取到一级分类的pid
        String pid = eduSubjectOne.getId();
        EduSubject exitSubjectTwo = this.exitSubjectTwo(subjectService, excelData.getTwoSubjectName(),pid);
        if(exitSubjectTwo == null){
            exitSubjectTwo = new EduSubject();
            exitSubjectTwo.setParentId(pid);
            exitSubjectTwo.setTitle(excelData.getTwoSubjectName());
            subjectService.save(exitSubjectTwo);
        }
    }

    /**
     * 判断一级分类不能重复
     * @param subjectService
     * @param name
     */
    private EduSubject exitSubjectOne(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = subjectService.getOne(wrapper);
        return one;
    }

    /**
     * p判断二级分类不能重复添加
     * @param name
     * @param pid
     */
    private EduSubject exitSubjectTwo(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject two = subjectService.getOne(wrapper);
        return two;
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
