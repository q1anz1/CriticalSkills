package com.equestria.criticalskills.criticalskills.service.userService;

import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.LoginDTO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetByEmailDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetBySecurityDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.User;

import java.util.List;


public interface UserService {


    void addUser(RegisterDTO registerDTO);


    boolean login(LoginDTO loginDTO);

    void updatePasswordBySecurity(ForgetBySecurityDTO forgetBySecurityDTO);

    void updatePasswordByEmail(ForgetByEmailDTO forgetByEmailDTO);

    User getUserById(Long id);

    void updateUser(User user);

    void clearUser(Long id);



    void uploadVideo(Long id, String url);

    void uploadAvator(Long id, String url);


    void uploadPhoto(Long id, String url);
}
