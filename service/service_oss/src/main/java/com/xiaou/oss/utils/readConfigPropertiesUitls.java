package com.xiaou.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * TOOD
 *
 * @Description
 * @Author xiaou
 * @Date$ 2023/4/19
 * 读取OSS配置文件中的内容的类
 **/
@Component
public class readConfigPropertiesUitls implements InitializingBean {
    //定义公开静态常量
    public static String END_PPINT;

    public static String KEY_ID;

    public static String KEY_SECRET;

    public static String BACKET_NAME;


    //读取配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keySecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    @Override
    public void afterPropertiesSet() throws Exception {
        END_PPINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BACKET_NAME = bucketName;
    }
}
