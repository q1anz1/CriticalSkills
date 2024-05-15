package com.equestria.criticalskills.criticalskills.service.userService;

import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.LoginDTO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetByEmailDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetBySecurityDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.User;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.result.Result;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface UserService {


    void addUser(RegisterDTO registerDTO);


    boolean login(LoginDTO loginDTO);

    void updatePasswordBySecurity(ForgetBySecurityDTO forgetBySecurityDTO);

    void updatePasswordByEmail(ForgetByEmailDTO forgetByEmailDTO);

    void uploadVideo(Long id, String url);

    void uploadAvator(Long id, String url);

    void uploadPhoto(Long id, String url);

    User getUserById(Long id);

    void updateUser(User user , HttpServletRequest httpServletRequest);

    void clearUser(Long id , HttpServletRequest httpServletRequest);

}
