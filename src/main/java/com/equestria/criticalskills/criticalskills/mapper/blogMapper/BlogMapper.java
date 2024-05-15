package com.equestria.criticalskills.criticalskills.mapper.blogMapper;

import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.BlogEntity;
import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.LikesDislikesEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface BlogMapper {
    @Select("select username from critical_skills_works.user_info where id=#{owner_id}")
    String selectOwnerNameByOwnerId(Integer ownerId);//创建博客中通过用户Id查询用户名称

    @Insert("insert into critical_skills_works.blog_info(owner_name,owner_id,title,text,photoUrl,videoUrl,create_time,update_time) " +
            "values (#{ownerName},#{ownerId},#{title},#{text},#{photoUrl},#{videoUrl},#{createTime},#{updateTime})")
    void insertBlogInfo(BlogEntity blogEntity);//插入新博客

    @Select("select * from critical_skills_works.blog_info where blog_id=#{owner_id} and role=1")
    BlogEntity selectBlogInfoById(Integer id);//查看一篇博客的内容

    @Select("select * from critical_skills_works.likes_dislikes where blog_id=#{blogId} and user_id=#{userId}")
    LikesDislikesEntity selectIsLikedById(Integer blogId, Integer userId);//查看一篇博客是否被该用户点赞反对

    @Select("select * from critical_skills_works.blog_info  where role=1")
    List<BlogEntity> selectBlogsList ();//查看博客列表

    @Select("select * from critical_skills_works.blog_info where title like CONCAT('%', #{searchText}, '%') or text like CONCAT('%', #{searchText}, '%')")
    List<BlogEntity> selectBlogsListByText(String searchText);//搜索博客

    @Update("update critical_skills_works.blog_info set role=0 where blog_id=#{id}")
    void updateRoleTo0ById(Integer id);//逻辑删除

    @Select("select id from critical_skills_works.user_info where username=#{username}")
    Integer selectUserIdByUsername(String username);//通过用户名得到用户id

    @Select("select owner_id from critical_skills_works.blog_info where blog_id=#{BlogId}")
    Integer selectOwnerIdByBlogId(Integer BlogId);//通过博客id查找创建者id

    @Select("select * from critical_skills_works.likes_dislikes where blog_id=#{blogId} and user_id=#{userId}")
    LikesDislikesEntity selectIsLikeByUserIdAndBlogId(Integer blogId,Integer userId);//通过用户id和博客id检查是否已经点赞或者反对

    @Insert("insert into critical_skills_works.likes_dislikes(like_dislike, blog_id, user_id) values (#{likeDislike},#{blogId},#{userId})")
    void insertLikeDisLikeIntoLikesDislikes(Integer likeDislike,Integer blogId,Integer userId);//新插入一条博客的点赞或反对

    @Update("update critical_skills_works.likes_dislikes set like_dislike=#{likeDislike} where blog_id=#{blogId} and user_id=#{userId}")
    void updateLikeDisLike(Integer likeDislike,Integer blogId,Integer userId);//在原有的点赞或者反对上修改

    @Update("update critical_skills_works.likes_dislikes set like_dislike=0 where blog_id=#{blogId} and user_id=#{userId}")
    void updateLikeDisLikeSet0(Integer blogId,Integer userId);//删除博客的点赞或者反对
}
