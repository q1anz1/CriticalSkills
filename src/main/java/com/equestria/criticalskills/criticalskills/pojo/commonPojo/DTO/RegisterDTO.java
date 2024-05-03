package com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegisterDTO {

    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private LocalDate birthDate;
    private String email;
    private String emailCode;
    private String securityAsk;
    private String securityAns;
    private String phone;
    private String qq;
    private String invite;
}
