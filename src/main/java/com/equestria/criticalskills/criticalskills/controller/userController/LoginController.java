package com.equestria.criticalskills.criticalskills.controller.userController;


import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class LoginController {

    private final UserService userService;
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }




    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO) {

            userService.addUser(registerDTO);
            return Result.success("注册成功");

    }


}
