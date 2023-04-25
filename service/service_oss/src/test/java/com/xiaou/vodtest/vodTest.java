package com.xiaou.vodtest;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.aliyuncs.DefaultAcsClient;

import java.util.List;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/25
 **/
public class vodTest {
    public static void main(String[] args) throws ClientException {
        String accessKeyId = "LTAI5tDhtTLhwuo6v7wu3v9J";
        String accessKeySecret = "9ZW3io9CyqDy3y6smI2eJCAgHTUE8W";

        String title = "6 - What If I Want to Move Faster - upload by sdk";   //上传之后文件名称
        String fileName = "F:/6 - What If I Want to Move Faster.mp4";  //本地文件路径和名称
        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }

    }
    public static void getPlayAuth() throws Exception{
        //根据视频id获取视频凭证
        DefaultAcsClient client = initVod.initVodClient("LTAI5tDhtTLhwuo6v7wu3v9J", "9ZW3io9CyqDy3y6smI2eJCAgHTUE8W");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("9de8dc30e31771edba376723a78f0102");
        response =client.getAcsResponse(request);
        System.out.println(response.getPlayAuth());
    }

    //获取视频地址的方法
    public static void getVideoUrl() throws Exception{
        //1.初始化连接对象
        DefaultAcsClient client = initVod.initVodClient("LTAI5tDhtTLhwuo6v7wu3v9J", "9ZW3io9CyqDy3y6smI2eJCAgHTUE8W");

        //2.创建获取获取视频的request和response对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //根据视频id来获取视频地址
        request.setVideoId("9de8dc30e31771edba376723a78f0102");

        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

}
