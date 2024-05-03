package com.equestria.criticalskills.criticalskills.controller.commonController;

import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.EmailSendDTO;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.utils.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailSender emailSender;
    private final RedisTemplate<String,String>redisTemplate;


    @PostMapping("/send_verify_code")
    public Result sendEmail(@RequestBody EmailSendDTO emailSendDTO) throws MessagingException {
        String username = emailSendDTO.getUsername();
        String verification=emailSendDTO.getVerification();
        String realVerification=redisTemplate.opsForValue().get(username+"Verification");
        if (!verification.equals(realVerification)){
            return Result.error("图片验证码错误");
        }

        emailSender.sendVerifyCode(emailSendDTO);
        return Result.success("验证码发送成功");
    }


}
