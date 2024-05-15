package com.equestria.criticalskills.criticalskills.controller.userController;


import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.LoginDTO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetByEmailDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetBySecurityDTO;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;
import com.equestria.criticalskills.criticalskills.utils.JsonWebTokenUtil;
import com.equestria.criticalskills.criticalskills.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JsonWebTokenUtil jsonWebTokenUtil;

    @PostMapping("/log/register")
    public Result<String> register(@RequestBody RegisterDTO registerDTO) {
            userService.addUser(registerDTO);
            return Result.success("注册成功");

    }

    @PostMapping("/log/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest httpServletRequest) {
        var visitor=httpServletRequest.getAttribute("visitor");

        if(userService.login(loginDTO)){

        }
        if(true
                //userService.login(loginDTO)
            ){

            Map<String, Object> claims = new HashMap<>();
            claims.put("username",loginDTO.getUsername());
            String token= jsonWebTokenUtil.createToken("jwt",claims,180);
            httpServletRequest.removeAttribute("visitor");
            return Result.success("用户登录成功,jwt令牌为: "+token);
        } else if(visitor!=null){
            log.info("有visitor标记,以游客身份登录");
            return Result.success("游客登录成功");
        }else {
            return Result.error("登录失败");
        }

    }

    @PostMapping("/log/forget_security")
    public Result forgetBySecurity(@RequestBody ForgetBySecurityDTO forgetBySecurityDTO) {
        userService.updatePasswordBySecurity(forgetBySecurityDTO);
        return Result.success("密码修改完成");
    }

    @PostMapping("/log/forget_email")
    public Result forgetByEmail(@RequestBody ForgetByEmailDTO forgetByEmailDTO) {
        userService.updatePasswordByEmail(forgetByEmailDTO);
        return Result.success("密码修改完成");
    }


}
