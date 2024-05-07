package com.equestria.criticalskills.criticalskills.utils;

import cn.hutool.json.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {

    private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    private String accessKeyId = "LTAI5tRvYdjnRvUKbpkDQqwt";
    private String accessKeySecret = "S8zAjVmpXZgGAGvn9OgtdEzms6lj9z";
    private String bucketName = "critical-skills";




    /**
     * 实现上传图片到OSS
     */
    public String upload (MultipartFile file) throws IOException {
        //获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        //创建文件的uuid
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        ossClient.shutdown();
        return url;
    }


    //根据OSS图片url得到缩略图url
    public static String getThumbnailUrl(String imgUrl){
        imgUrl = imgUrl + "?x-oss-process=image/resize,m_fill,w_400,quality,q_60";
        System.out.println(">>>>>>>>>>>>>>>>> 缩略图url:"+imgUrl);
        return imgUrl;
    }

    //根据OSS视频url得到视频封面图url
    public static String getVideoCoverlUrl(String videoUrl){
        videoUrl = videoUrl + "?x-oss-process=video/snapshot,t_7000,f_jpg,w_800,h_600,m_fast";
        System.out.println(">>>>>>>>>>>>>>>>> 视频封面图url:"+videoUrl);
        return videoUrl;
    }
}

