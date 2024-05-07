package com.equestria.criticalskills.criticalskills.mapper.userMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper extends BaseMapper<User> {

@Select("SELECT * FROM user_info WHERE id = #{id}")
    User selectById(Long id);

    int updateById(User user);

    void clearUserFields(Long id);

}