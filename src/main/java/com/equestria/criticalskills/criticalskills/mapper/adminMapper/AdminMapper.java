package com.equestria.criticalskills.criticalskills.mapper.adminMapper;


import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.SelectUserDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.Account;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;


@Mapper
public interface AdminMapper {



    void deleteUsers(List<String> usernames);

    @Select("select * from account where username=#{username}")
    Account findUserByUsername(String username);

    List<UserInfo> selectUserInfos(SelectUserDTO selectUserDTO);

    void updateUser(UserInfo userInfo);

}
