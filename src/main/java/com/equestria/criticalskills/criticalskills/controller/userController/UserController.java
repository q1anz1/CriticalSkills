package com.equestria.criticalskills.criticalskills.controller.userController;
import cn.hutool.core.util.RandomUtil;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.User;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;

import com.equestria.criticalskills.criticalskills.utils.JsonWebTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //根据id返回个人信息
    @GetMapping("/user/find_user")
    public Result getUserById(@RequestParam Long id){
        User user = userService.getUserById(id);
        if (user == null){
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }


    //修改
    @PutMapping("/user/modify_user")

    public Result  updateUser(@RequestBody User user ,HttpServletRequest httpServletRequest){
        userService.updateUser(user, httpServletRequest);
        return Result.success("修改成功");

    }

    //清空个人信息
    @PostMapping("/user/clear_user")
    public Result clearUser(@RequestParam Long id , HttpServletRequest httpServletRequest) {
        userService.clearUser(id , httpServletRequest);

        return Result.success("清空成功");
    }

    }
