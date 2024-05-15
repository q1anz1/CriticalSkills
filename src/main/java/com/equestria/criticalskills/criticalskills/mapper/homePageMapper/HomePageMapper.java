package com.equestria.criticalskills.criticalskills.mapper.homePageMapper;

import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface HomePageMapper {
     
     List<UserInfo> list (String username, Integer gender, Integer ageStart,Integer ageEnd
            , String email, String qq, String phone);

     @Select("select * from critical_skills_works.user_info where id = #{id}")
     UserInfo selectUserBasicInfoById(Integer id);
     @Select("select count(*) from critical_skills_works.user_info")
     Integer selectNumberOfUser();
     @Select("select role from critical_skills_works.account where id=#{id}")
     Integer selectRoleById(Integer id);


}
