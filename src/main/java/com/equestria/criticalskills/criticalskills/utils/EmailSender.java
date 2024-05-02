package com.equestria.criticalskills.criticalskills.utils;


import cn.hutool.extra.template.TemplateEngine;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.EmailSendDTO;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;


@Service
public class EmailSender {

        @Resource
        private JavaMailSenderImpl mailSender;


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

        public void sendVerifyCode(EmailSendDTO emailSendDTO) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String to=emailSendDTO.getEmail();
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("邮箱验证码");
            helper.setText(generateVerifyCode(6));
            mailSender.send(message);
        }


        public void sendSystemMessage(EmailSendDTO emailSendDTO) {
        }


}
