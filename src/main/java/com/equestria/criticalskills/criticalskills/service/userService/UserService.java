package com.equestria.criticalskills.criticalskills.service.userService;

import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.LoginDTO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetByEmailDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetBySecurityDTO;

public interface UserService {


    void addUser(RegisterDTO registerDTO);


    boolean login(LoginDTO loginDTO);

    void updatePasswordBySecurity(ForgetBySecurityDTO forgetBySecurityDTO);

    void updatePasswordByEmail(ForgetByEmailDTO forgetByEmailDTO);
}
