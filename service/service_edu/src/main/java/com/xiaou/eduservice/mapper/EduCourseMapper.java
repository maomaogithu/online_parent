package com.xiaou.eduservice.mapper;

import com.xiaou.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.eduservice.entity.vo.CrousePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xiaou
 * @since 2023-04-22
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    //通过id去查询所需课程信息

    public CrousePublishVo getCrousePublisInfo(String crouseId);

}
