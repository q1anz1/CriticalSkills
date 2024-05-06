package com.equestria.criticalskills.criticalskills.utils;


import cn.hutool.extra.template.TemplateEngine;
import com.equestria.criticalskills.criticalskills.config.RedisConfig;
import com.equestria.criticalskills.criticalskills.exception.EmailException;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.EmailSendDTO;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSender {

        private final RedisTemplate<String,String> redisTemplate;
        private final JavaMailSenderImpl mailSender;


        @Value("${spring.mail.username}")
        private String from;


        private static final String SYMBOLS = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        private static final Random RANDOM = new SecureRandom();
        public static String generateVerifyCode(int len) {
            char[] numbers = new char[len];
            for (int i = 0; i < len; i++) {
                numbers[i] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
            }
            return new String(numbers);
        }

        //发送6位验证码
        public void sendVerifyCode(EmailSendDTO emailSendDTO) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String username=emailSendDTO.getUsername();
            String verification=emailSendDTO.getVerification();
            String realVerification=redisTemplate.opsForValue().get(username+"Verification");
            if (!verification.equals(realVerification)){
                throw new EmailException("图片验证码不正确");
            }
            String to=emailSendDTO.getEmail();
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("邮箱验证码");
            String emailCode = generateVerifyCode(6);
            helper.setText("本次验证码为: "+emailCode+" ,该验证码三分钟内有效,此消息为系统自动发送,无需回复");
            mailSender.send(message);
            redisTemplate.opsForValue().set(to+"EmailCode",emailCode);
            redisTemplate.expire(to+"EmailCode",180, TimeUnit.SECONDS);
            log.info("验证码发送成功");
        }


        public void sendSystemMessage(EmailSendDTO emailSendDTO) {
        }


}
