package com.equestria.criticalskills.criticalskills.mapper.blogMapper;

import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.BlogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface BlogMapper {
    @Select("select username from critical_skills_works.user_info where id=#{owner_id}")
    String selectOwnerNameByOwnerId(Integer ownerId);//创建博客中通过用户Id查询用户名称

    @Insert("insert into critical_skills_works.blog_info(owner_name,owner_id,title,text,photoUrl,videoUrl,create_time,update_time) " +
            "values (#{ownerName},#{ownerId},#{title},#{text},#{photoUrl},#{videoUrl},#{createTime},#{updateTime})")
    void insertBlogInfo(BlogEntity blogEntity);//插入新博客

    @Select("select * from critical_skills_works.blog_info where id=#{owner_id}")
    BlogEntity selectBlogInfoById(Integer id);//查看一篇博客

    @Select("select * from critical_skills_works.blog_info ")
    List<BlogEntity> selectBlogsList ();//查看博客列表

    @Select("select * from critical_skills_works.blog_info where title like CONCAT('%', #{searchText}, '%') or text like CONCAT('%', #{searchText}, '%')")
    List<BlogEntity> selectBlogsListByText(String searchText);//搜索博客
}
