package com.equestria.criticalskills.criticalskills.controller.userController;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.User;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {
    @Autowired
    private UserService userService;

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
    public Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success();
    }

    //清空个人信息
    @PostMapping("/user/clear_user")
    public Result clearUser(@RequestParam Long id) {
        userService.clearUser(id);
        return Result.success();
    }

    //条件分页查询
/*    @GetMapping
    public Result searchUsers(@RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer gender,
                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                              @RequestParam(required = false) String province,
                              @RequestParam(required = false) String city,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int pageSize) {
        PageBean userPage = userService.queryUser(name, gender, begin, end, province, city, page, pageSize);
        return Result.success(userPage);
    }*/


    }
