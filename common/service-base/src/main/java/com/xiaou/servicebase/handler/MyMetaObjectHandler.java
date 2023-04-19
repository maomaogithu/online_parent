package com.xiaou.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/14
 * 自动填充类
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER= LoggerFactory.getLogger(MyMetaObjectHandler.class);
    @Override
    public void insertFill(MetaObject metaObject) {
        //LOGGER.info("start insert fill ...");
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

}
