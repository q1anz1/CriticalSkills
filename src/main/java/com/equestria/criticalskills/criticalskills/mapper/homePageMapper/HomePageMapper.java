package com.equestria.criticalskills.criticalskills.mapper.homePageMapper;

import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface HomePageMapper {
     
     List<UserBasicInfo> list (String username, Integer gender, Integer ageStart,Integer ageEnd
            , String email, String qq, String phone);

     @Select("select * from critical_skills_works.user_basic_info where id = #{id}")
     UserBasicInfo selectUserBasicInfoById(Integer id);
     @Select("select count(*) from critical_skills_works.user_basic_info")
     Integer selectNumberOfUser();
     @Select("select role from critical_skills_works.account where id=#{id}")
     Integer selectRoleById(Integer id);
}
