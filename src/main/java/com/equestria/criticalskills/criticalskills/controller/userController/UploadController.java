package com.equestria.criticalskills.criticalskills.controller.userController;

import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;
import com.equestria.criticalskills.criticalskills.utils.AliOSSUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final AliOSSUtils aliOSSUtils;
    private final UserService userService;


    //上传头像
    @PutMapping("/uploadAvator")
    public Result uploadAvator(@RequestParam Long id, @RequestParam(required = false) MultipartFile avator) throws IOException {
        String url = aliOSSUtils.upload(avator);
        log.info("上传头像的url: {}", url);
        userService.uploadAvator(id, url);
        return Result.success();
    }

    //上传图片
    @PutMapping("/uploadPhotos")
    public Result uploadPhoto(@RequestParam Long id , @RequestParam MultipartFile[] photos) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile photo : photos) {
            if (!photo.isEmpty()) {
                try {
                    String url = aliOSSUtils.upload(photo);
                    log.info("上传图片的url: {}", url);
                    urls.add(url);
                } catch (IOException e) {
                    log.error("上传图片时发生错误", e);
                    return Result.error("上传图片时发生错误");
                }
            }
        }
        String url = urls.toString();
        log.info(url);
        userService.uploadPhoto(id, url);
        return Result.success(urls);

    }

    //上传视频
    @PutMapping("/uploadVideo")
    public Result uploadVideo(@RequestParam Long id , @RequestParam MultipartFile[] videos) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile video : videos) {
            if (!video.isEmpty()) {
                try {
                    String url = aliOSSUtils.upload(video);
                    log.info("上传视频的url: {}", url);
                    urls.add(url);
                } catch (IOException e) {
                    log.error("上传视频时发生错误", e);
                    return Result.error("上传视频时发生错误");
                }
            }
        }
        String url = urls.toString();
        log.info(url);
        userService.uploadVideo(id, url);
        return Result.success(urls);

    }


}
