package com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO;

import lombok.Data;

@Data
public class ForgetBySecurityDTO {
    private String username;
    private String securityAsk;
    private String securityAns;
    private String password;
}
