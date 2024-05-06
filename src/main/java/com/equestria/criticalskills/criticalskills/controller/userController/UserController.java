package com.equestria.criticalskills.criticalskills.controller.userController;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.User;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stu")
public class UserController {
    @Autowired
    private UserService userService;

    //根据id返回个人信息
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return Result.success(user);
    }


    //修改
    @PutMapping
    public Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success();
    }

    //清空个人信息
    @PostMapping("/{id}")
    public Result clearUser(@PathVariable Long id) {
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
