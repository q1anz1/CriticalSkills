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

    @Select("select * from account where id=#{id}")
    Account findUserByUserId(Long id);

    List<UserInfo> selectUserInfos(SelectUserDTO selectUserDTO);

    void updateUserInfo(UserInfo userInfo);

    void updateAccount(UserInfo userInfo);

    @Select("select photo from user_info where username=#{username}")
    String findPhotos(String username);

    @Select("select video from user_info where username=#{username}")
    String findVideos(String username);


    void deletePhotosAndVideos(String username,String newPhotos, String newVideos);
}
