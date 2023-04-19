package com.xiaou.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.xiaou.oss.service.OssService;
import com.xiaou.oss.utils.readConfigPropertiesUitls;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/19
 **/
@Service
public class OssSrviceImpl implements OssService {

    @Override
    public String uploadAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = readConfigPropertiesUitls.END_PPINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = readConfigPropertiesUitls.KEY_ID;
        String accessKeySecret = readConfigPropertiesUitls.KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = readConfigPropertiesUitls.BACKET_NAME;

        //获取文件名称
        String fileName = file.getOriginalFilename();
        //1.在文件名后面加上一个随机唯一的id用来区分相同名称的文件
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        fileName = uuid +fileName ;

        //2.按照日期对文件进行分类 例子：2023/4/19/001.jpg
        //获取当前日期
        String date = new DateTime().toString("yyyy/MM/dd");
        //拼接格式
        fileName = date+"/"+fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
            // 设置该属性可以返回response。如果不设置，则返回的response为空。
            putObjectRequest.setProcess("true");
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 如果上传成功，则返回200。
            System.out.println(result.getResponse().getStatusCode());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
                //返回上传文件后的路径
                String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
                return url;
            }
        }
        return null;
    }
}
