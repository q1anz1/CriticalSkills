package com.equestria.criticalskills.criticalskills.controller.adminController;


import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.SystemMsgDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.SelectUserDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.result.PageResult;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.adminService.AdminService;
import jakarta.mail.MessagingException;
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
    public Result deleteUsers(@RequestParam List<String> usernames) {

        adminService.deleteUsers(usernames);
        return Result.success("删除用户成功");

    }

    @PostMapping("/admin/find_user")
    public Result<PageResult> findUser(@RequestBody SelectUserDTO selectUserDTO) {
        PageResult pageResult=adminService.selectUsers(selectUserDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/admin/system_msg")
    public Result sendSystemMsg(@RequestBody SystemMsgDTO systemMsgDTO) throws MessagingException {
        adminService.sendSystemMsg(systemMsgDTO);
        return Result.success("系统消息已成功发送");
    }

    @PutMapping("/admin/edit_user")
    public Result editUser(@RequestBody UserInfo userInfo){
        adminService.editUser(userInfo);
        return Result.success("用户信息修改成功");

    }

    @DeleteMapping("/admin/delete_photo_vedio")
    public Result deletePhotoAndVedio(@RequestParam String username,@RequestParam List<Integer> photos, @RequestParam List<Integer> videos){
        adminService.deletePhotoAndVedio(username,photos,videos);
        return Result.success("删除成功");

    }


}
