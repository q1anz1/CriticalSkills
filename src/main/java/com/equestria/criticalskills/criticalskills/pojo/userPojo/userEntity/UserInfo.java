package com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.OffsetDateTime;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserInfo {
    private String username;
    private Integer age;
    private Integer gender;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String qq;
    private String avator;
    private String province;
    private String city;
    private String introduction;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}

