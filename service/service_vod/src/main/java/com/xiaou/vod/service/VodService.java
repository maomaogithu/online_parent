package com.xiaou.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/25
 **/
public interface VodService {

    String uploadVideoAly(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);
}
