package com.equestria.criticalskills.criticalskills.controller.adminController;


import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.SelectUserDTO;
import com.equestria.criticalskills.criticalskills.result.PageResult;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.adminService.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final AdminService adminService;

    @DeleteMapping("/admin/delete_user")
    public Result deleteUsers(@RequestParam List<String> ids) {

        adminService.deleteUsers(ids);
        return Result.success("删除用户成功");

    }

    @PostMapping("/admin/find_user")
    public Result<PageResult> findUser(@RequestBody SelectUserDTO selectUserDTO) {
        PageResult pageResult=adminService.selectUsers(selectUserDTO);
        return Result.success(pageResult);
    }


}
