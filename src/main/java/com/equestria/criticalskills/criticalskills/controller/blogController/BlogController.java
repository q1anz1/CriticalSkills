package com.equestria.criticalskills.criticalskills.controller.blogController;

import cn.hutool.core.util.RandomUtil;
import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.DTO.NewBlogDTO;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.blogService.BlogService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BlogController {
    private String secret = RandomUtil.randomString(64);

    private final BlogService blogService;
    @PostMapping("/blogs/new")//新建博客
    public Result newBlog(@RequestBody NewBlogDTO newBlogDTO, @RequestBody HttpServletRequest httpServletRequest){
        log.info("新建博客");
        return blogService.newBlog(newBlogDTO);
    }

    @GetMapping("/blogs/blog")//查看一篇博客
    public Result readBlog(@RequestParam Integer id){
        log.info("查看博客id={}",id);
        return blogService.readBlog(id);
    }

    @GetMapping("/blogs")//分页查看博客列表
    public Result blogsList(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询blog页数：{},每页有{}",page,pageSize);
        return blogService.blogsList(page,pageSize);
    }
    @GetMapping("/blogs/search")
    public Result searchBlogsList(@RequestParam String searchText,@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("搜索博客:{},分页{},{}",searchText,page,pageSize);
        return blogService.searchBlogsList(searchText,page,pageSize);
    }
}
