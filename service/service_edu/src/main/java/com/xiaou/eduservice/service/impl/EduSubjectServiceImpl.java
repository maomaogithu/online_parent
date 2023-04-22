package com.xiaou.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.eduservice.entity.EduSubject;
import com.xiaou.eduservice.entity.excel.subjectSort;
import com.xiaou.eduservice.entity.sub.level1;
import com.xiaou.eduservice.entity.sub.level2;
import com.xiaou.eduservice.linstener.SubjectListener;
import com.xiaou.eduservice.mapper.EduSubjectMapper;
import com.xiaou.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-21
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            //获取文件输入流
            InputStream fileInputStream = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(fileInputStream, subjectSort.class,new SubjectListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<level1> getAllRecords() {
        //获取所有的一级记录
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> listOne = baseMapper.selectList(wrapperOne);

        //查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");//不等于
        List<EduSubject> listTwo = baseMapper.selectList(wrapperTwo);

        //创建集合用来封装所需的数据
        ArrayList<level1> finaList = new ArrayList<>();

        //封装一级分类
        for (int i = 0; i <listOne.size() ; i++) {
            EduSubject subject = listOne.get(i);
            level1 level1 = new level1();
            BeanUtils.copyProperties(subject,level1);
            finaList.add(level1);
            //创建集合来封装每个一级分类的二级分类
            ArrayList<level2> finalListTwo = new ArrayList<>();
            //循环遍历二级分类
            for (int k = 0; k < listTwo.size(); k++) {
                EduSubject subject1 = listTwo.get(k);//获取二级分类的信息
                if(subject.getId().equals(subject1.getParentId())){//如果二级分类的parent_id 等于一级分类的id
                    level2 level2 = new level2();
                    BeanUtils.copyProperties(subject1,level2);
                    finalListTwo.add(level2);
                }
            }
            level1.setChildren(finalListTwo);
        }
        return finaList;
    }
}
