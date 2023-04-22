package com.xiaou.eduservice.service;

import com.xiaou.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.eduservice.entity.sub.level1;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xiaou
 * @since 2023-04-21
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<level1> getAllRecords();
}
