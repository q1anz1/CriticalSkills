package com.equestria.criticalskills.criticalskills;

import com.equestria.criticalskills.criticalskills.mapper.adminMapper.AdminMapper;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.UserInfoMapper;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.SelectUserDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CriticalSkillsApplicationTests {
@Resource
AdminMapper adminMapper;
    @Test
    void contextLoads() {
        for (UserInfo selectUserInfo : adminMapper.selectUserInfos(new SelectUserDTO())) {
            System.out.println(selectUserInfo);
        }
    }

}
