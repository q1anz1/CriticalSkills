package com.equestria.criticalskills.criticalskills.service.blogService;

import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.DTO.NewBlogDTO;
import com.equestria.criticalskills.criticalskills.result.Result;
import jakarta.servlet.http.HttpServletRequest;

public interface BlogService {
    Result newBlog(NewBlogDTO newBlogDTO, HttpServletRequest httpServletRequest);//新建博客

    Result readBlog(Integer id,HttpServletRequest httpServletRequest);//查看一篇博客

    Result blogsList(Integer page, Integer pageSize);//查看博客列表

    Result searchBlogsList(String searchText,Integer page,Integer pageSize);//搜索博客

    Result deleteBlog(Integer id,HttpServletRequest httpServletRequest);//删除博客

    Result like(Integer isLike,Integer isBlog,Integer targetId,HttpServletRequest httpServletRequest);//点赞或者反对

    Result cancel(Integer targetId,Integer isBlog,HttpServletRequest httpServletRequest);//取消点赞和反对
}
