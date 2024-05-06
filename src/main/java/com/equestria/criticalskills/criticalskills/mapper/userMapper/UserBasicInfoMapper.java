package com.equestria.criticalskills.criticalskills.mapper.userMapper;

import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBasicInfoMapper {

    @Insert("insert into user_info(username, age, gender, birth_date, email, phone, qq, avator, create_time,update_time) "+
            "VALUES (#{username},#{age},#{gender},#{birthDate},#{email},#{phone},#{qq},#{avator},#{createTime},#{updateTime})")
    void insertUserBasicInfo(UserInfo userInfo);


}
