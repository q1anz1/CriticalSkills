package com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO;

import lombok.Data;

@Data
public class ForgetByEmailDTO {
    private String username;
    private String email;
    private String emailCode;
    private String password;
}
