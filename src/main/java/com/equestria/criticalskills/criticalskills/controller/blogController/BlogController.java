package com.equestria.criticalskills.criticalskills.controller.blogController;


import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.DTO.NewBlogDTO;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.blogService.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogService blogService;
    @PostMapping("/blogs/new")//新建博客
    public Result newBlog(@RequestBody NewBlogDTO newBlogDTO, @RequestBody HttpServletRequest httpServletRequest){
        log.info("新建博客");
        return blogService.newBlog(newBlogDTO,httpServletRequest);
    }

    @GetMapping("/blogs/blog")//查看一篇博客
    public Result readBlog(@RequestParam Integer blogId, HttpServletRequest httpServletRequest){
        log.info("查看博客id={}",blogId);
        return blogService.readBlog(blogId,httpServletRequest);
    }

    @GetMapping("/blogs")//分页查看博客列表
    public Result blogsList(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询blog页数：{},每页有{}",page,pageSize);
        return blogService.blogsList(page,pageSize);
    }
    @GetMapping("/blogs/search")//分页模糊搜索博客
    public Result searchBlogsList(@RequestParam String searchText,@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("搜索博客:{},分页{},{}",searchText,page,pageSize);
        return blogService.searchBlogsList(searchText,page,pageSize);
    }

    @GetMapping("/blogs/delete")//删除博客
    public Result deleteBlog(@RequestParam Integer id, @RequestBody HttpServletRequest httpServletRequest){
        log.info("删除博客：id={}",id);
        return  blogService.deleteBlog(id,httpServletRequest);
    }

    @GetMapping("/blogs/like")//点赞或者反对
    public Result like(@RequestParam Integer targetId,@RequestParam Integer isLike,@RequestParam Integer isBlog,HttpServletRequest httpServletRequest){
        log.info("是否点赞：{}，目标:{},是否博客：{}",isLike,targetId,isBlog);
        return blogService.like(isLike,isBlog,targetId,httpServletRequest);
    }

    @GetMapping("/blogs/cacel")//取消点赞或者反对
    public Result cancelLike(@RequestParam Integer targetId,@RequestParam Integer isBlog,HttpServletRequest httpServletRequest){
        log.info("取消点赞：id={}，是否为博客：{}",targetId,isBlog);
        return blogService.cancel(targetId,isBlog,httpServletRequest);
    }
}
