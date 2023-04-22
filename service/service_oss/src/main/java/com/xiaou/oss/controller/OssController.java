package com.xiaou.oss.controller;

import com.xiaou.commonutils.R;
import com.xiaou.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/19
 **/
@RestController
@RequestMapping("/eduoss/uploadFileOss")
@CrossOrigin
@Api(value = "上传OSS接口")
public class OssController {
    @Autowired
    private OssService ossService;

    /**
     * 上传OSS的cotroller层方法
     * @return
     */
    @ApiOperation(value = "上传文件")
    @PostMapping("/uploadFile")
    public R uploadFileOss(MultipartFile file){
       String returnUrl =  ossService.uploadAvatar(file);
        return R.ok().data("url",returnUrl);
    }
}
