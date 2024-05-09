package com.equestria.criticalskills.criticalskills.controller.userController;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
public class UserController {
    @Autowired
    private UserService userService;

    //根据id返回个人信息
    @GetMapping("/user/find_user")
    public Result getUserById(@RequestParam Long id){
<<<<<<< Updated upstream
        User user = userService.getUserById(id);
        if (user == null){
=======
        UserInfo user = userService.getUserById(id);
        if(user==null){
>>>>>>> Stashed changes
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }


    //修改
    @PutMapping("/user/modify_user")
<<<<<<< Updated upstream
    public Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success("修改成功");
=======
    public Result updateUser(@RequestBody UserInfo user){
        userService.updateUser(user);
        return Result.success("success");
>>>>>>> Stashed changes
    }

    //清空个人信息
    @PostMapping("/user/clear_user")
<<<<<<< Updated upstream
    public Result clearUser(@RequestParam Long id) {
        userService.clearUser(id);
        return Result.success("清空成功");
=======
    public Result clearUser(@RequestParam Integer id) {
        userService.clearUser(Long.valueOf(id));
        return Result.success("success");
>>>>>>> Stashed changes
    }


    }
