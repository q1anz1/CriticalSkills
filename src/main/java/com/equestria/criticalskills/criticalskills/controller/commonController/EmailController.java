package com.equestria.criticalskills.criticalskills.controller.commonController;

import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.EmailSendDTO;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.utils.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailSender emailSender;


    @PostMapping("/send_verify_code")
    public Result sendEmail(@RequestBody EmailSendDTO emailSendDTO) throws MessagingException {
        emailSender.sendVerifyCode(emailSendDTO);
        return Result.success("验证码发送成功");
    }


}
