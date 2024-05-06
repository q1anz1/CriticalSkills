package com.equestria.criticalskills.criticalskills.controller.userController;

import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;
import com.equestria.criticalskills.criticalskills.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private UserService userService;

    //上传图片
    @PutMapping ("/{id}/uploadImage")
    public Result uploadImage(@RequestParam(required = false)MultipartFile image , @PathVariable Long id) throws IOException {
        String url = aliOSSUtils.upload(image);
        log.info("上传文件的url",url);

        userService.uploadImage(id,url);
        return Result.success(url);

    }

    //上传视频
    @PutMapping ("/{id}/uploadVideo")
    public Result uploadVideo( @RequestParam(required = false)MultipartFile video , @PathVariable Long id) throws IOException {
        String url = aliOSSUtils.upload(video);
        log.info("上传文件的url",url);

        userService.uploadVideo(id,url);
        return Result.success(url);

    }
}
