package com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserBasicInfo {
    private String username;
    private Integer age;
    private Integer gender;
    private String email;
    private String phone;
    private String qq;
    private String introduction;
    private String avator;
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
