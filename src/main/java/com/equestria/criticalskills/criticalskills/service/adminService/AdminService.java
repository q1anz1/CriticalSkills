package com.equestria.criticalskills.criticalskills.service.adminService;

import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.SelectUserDTO;
import com.equestria.criticalskills.criticalskills.result.PageResult;

import java.util.List;

public interface AdminService {

    void deleteUsers(List<String> ids);


    PageResult selectUsers(SelectUserDTO selectUserDTO);
}
