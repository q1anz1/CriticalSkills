package com.equestria.criticalskills.criticalskills.service.blogService.blogServiceImpl;

import com.equestria.criticalskills.criticalskills.mapper.blogMapper.BlogMapper;
import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.BlogEntity;
import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.DTO.NewBlogDTO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO.BlogEntityPageBean;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.blogService.BlogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogMapper blogMapper;

    @Override
    public Result newBlog(NewBlogDTO newBlogDTO) {//新建博客
        //创建博客类
        BlogEntity blogEntity = new BlogEntity();
        //设置博客类属性
        blogEntity.setOwnerId(newBlogDTO.getOwnerId());
        blogEntity.setTitle(newBlogDTO.getTitle());
        blogEntity.setText(newBlogDTO.getText());
        blogEntity.setPhotoUrl(newBlogDTO.getPhotoUrl());
        blogEntity.setVideoUrl(newBlogDTO.getVideoUrl());
        //设置默认属性
        blogEntity.setCreateTime(LocalDateTime.now());
        blogEntity.setUpdateTime(LocalDateTime.now());
        blogEntity.setOwnerName(blogMapper.selectOwnerNameByOwnerId(newBlogDTO.getOwnerId()));
        //存入数据库
        blogMapper.insertBlogInfo(blogEntity);
        return Result.success();
    }

    @Override
    public Result readBlog(Integer id) {//查看一篇博客
        BlogEntity blogEntity = blogMapper.selectBlogInfoById(id);
        return Result.success(blogEntity);
    }

    @Override
    public Result blogsList(Integer page, Integer pageSize) {//查看博客列表
        PageHelper.startPage(page,pageSize);
        Page<BlogEntity> p = (Page<BlogEntity>) blogMapper.selectBlogsList();
        return Result.success(new BlogEntityPageBean((int) p.getTotal(),p.getResult()));
    }

    @Override
    public Result searchBlogsList(String searchText, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        Page<BlogEntity> p = (Page<BlogEntity>) blogMapper.selectBlogsListByText(searchText);
        return Result.success(new BlogEntityPageBean((int) p.getTotal(),p.getResult()));
    }
}
