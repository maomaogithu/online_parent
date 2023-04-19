package com.xiaou.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/19
 **/
public interface OssService {

    String uploadAvatar(MultipartFile file);

}
