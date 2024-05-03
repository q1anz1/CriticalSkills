package com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserBasicInfo {
    private String username;
    private Integer age;
    private Integer gender;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String qq;
    private String avator;
    private LocalDateTime updateTime;

}
