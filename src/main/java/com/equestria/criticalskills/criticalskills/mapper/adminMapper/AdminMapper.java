package com.equestria.criticalskills.criticalskills.mapper.adminMapper;


import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.SelectUserDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;


@Mapper
public interface AdminMapper {



    void deleteUsers(List<String> ids);


    List<UserInfo> selectUserInfos(SelectUserDTO selectUserDTO);

}
