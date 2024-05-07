package com.equestria.criticalskills.criticalskills.mapper.adminMapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


import java.util.List;


@Mapper
public interface AdminMapper {



    void deleteUsers(List<String> ids);


}
