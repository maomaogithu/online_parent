package com.xiaou.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaou.commonutils.R;
import com.xiaou.eduservice.entity.EduTeacher;
import com.xiaou.eduservice.entity.vo.queryTeacher;
import com.xiaou.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xiaou
 * @since 2023-04-13
 */
@RestController
@RequestMapping("/online/edu-teacher")
@Api(value = "教师管理接口")
@CrossOrigin
public class EduTeacherController {
    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;
    //调用service中的方法 rest风格
    @GetMapping("queryAll")
    @ApiOperation(value = "查询所有教师信息")
    public R queryAll(){
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return R.ok().data("item",teacherList);
    }
    //实现分页查询效果
    @ApiOperation(value = "分页查询教师信息")
    @GetMapping("page/{current}/{limit}")
    public R queryByPageTeacherList( @PathVariable long current,
                                    @PathVariable long limit){
        //1.创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        //2.
        eduTeacherService.page(page,null);
        //3.获取记录总数
        long total = page.getTotal();
        //4获取分页查询的数据
        List<EduTeacher> records = page.getRecords();

        HashMap map = new HashMap<>();
        map.put("total",total);
        map.put("limit",records);
        return R.ok().data(map);
    }

    //条件查询方法测试
    @ApiOperation(value = "条件分页查询教师信息")
    @PostMapping("pageTeacherCondtion/{current}/{limit}")
    public R queryTeacherCondtion(@PathVariable long current,
                                  @PathVariable long limit, @RequestBody(required = false) queryTeacher queryTeacher) {
        //1.创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        //2.构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //3.获取vo实体对象
        String name = queryTeacher.getName();//名称
        Integer level = queryTeacher.getLevel();//级别
        String begin = queryTeacher.getBegin();//开始时间
        String end = queryTeacher.getEnd();//结束时间
        //4.判断
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        eduTeacherService.page(page,wrapper);
        //3.获取记录总数
        long total = page.getTotal();
        //4获取分页查询的数据
        List<EduTeacher> records = page.getRecords();

        HashMap map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);

    }
    /**
     * 添加方法的接口
     */
    @PostMapping("addTeacher")
    @ApiOperation(value = "添加教师信息")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else
            return R.error();
    }
    /**
     * 删除
     */
    @ApiOperation(value = "逻辑删除教师信息")
    @DeleteMapping("{id}")
    public R deleteTeacherInfo(@PathVariable String id){
        boolean remove = eduTeacherService.removeById(id);
        if(remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
    /**
     * 根据id查询教师信息
     *
     */
    //根据讲师id进行查询
    @GetMapping("queryTeacherByid/{id}")
    @ApiOperation(value = "根据id查询教师信息")
    public R queryTeacherByid(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    /**
     * 修改
     */
    //讲师修改功能
    @PostMapping("updateTeacher")
    @ApiOperation(value = "更新教师信息")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

