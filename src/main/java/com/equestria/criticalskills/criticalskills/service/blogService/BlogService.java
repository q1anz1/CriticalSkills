package com.equestria.criticalskills.criticalskills.service.blogService;

import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.DTO.NewBlogDTO;
import com.equestria.criticalskills.criticalskills.result.Result;

public interface BlogService {
    Result newBlog(NewBlogDTO newBlogDTO);//新建博客

    Result readBlog(Integer id);//查看一篇博客

    Result blogsList(Integer page, Integer pageSize);//查看博客列表

    Result searchBlogsList(String searchText,Integer page,Integer pageSize);//搜索博客
}
