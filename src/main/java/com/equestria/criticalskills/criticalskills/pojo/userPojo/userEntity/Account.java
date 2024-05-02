package com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
    private String username;
    private String password;
    private String email;
    private String securityAsk;
    private String securityAns;
    private Integer role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
