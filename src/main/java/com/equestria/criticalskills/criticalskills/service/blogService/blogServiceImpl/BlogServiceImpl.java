package com.equestria.criticalskills.criticalskills.service.blogService.blogServiceImpl;

import com.equestria.criticalskills.criticalskills.mapper.blogMapper.BlogMapper;
import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.BlogEntity;
import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.DTO.NewBlogDTO;
import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.LikesDislikesEntity;
import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.VO.BlogVO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO.BlogEntityPageBean;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.blogService.BlogService;
import com.equestria.criticalskills.criticalskills.utils.JsonWebTokenUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogMapper blogMapper;
    private final JsonWebTokenUtil jsonWebTokenUtil;

    @Override
    public Result newBlog(NewBlogDTO newBlogDTO, HttpServletRequest httpServletRequest) {//新建博客
        //验证是否为该用户
        String username = blogMapper.selectOwnerNameByOwnerId(newBlogDTO.getOwnerId());//通过id得到用户名
        if(isRightUser(username,httpServletRequest)){//用户名相同
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
            blogEntity.setOwnerName(username);
            //存入数据库
            blogMapper.insertBlogInfo(blogEntity);
            return Result.success();
        }else{//创建博客者与当前登入用户不一致
            return Result.error("创建博客者与当前登入用户不一致！");
        }
    }

    @Override
    public Result readBlog(Integer blogId,HttpServletRequest httpServletRequest) {//查看一篇博客
        BlogEntity blogEntity = blogMapper.selectBlogInfoById(blogId);
        BlogVO blogVO = new BlogVO().setBlogVO(blogEntity);
        LikesDislikesEntity likesDislikesEntity=blogMapper.selectIsLikedById(blogId,getUserId(httpServletRequest));
        if(likesDislikesEntity!=null){
            if(likesDislikesEntity.getLikeDislike()==1){
                blogVO.setIsLike(1);
            }else if(likesDislikesEntity.getLikeDislike()==-1){
                blogVO.setIsLike(-1);
            } else{
                blogVO.setIsLike(0);
            }
            return Result.success(blogVO);
        }
        return Result.success(blogVO);

    }

    @Override
    public Result blogsList(Integer page, Integer pageSize) {//查看博客列表
        PageHelper.startPage(page,pageSize);
        Page<BlogEntity> p = (Page<BlogEntity>) blogMapper.selectBlogsList();
        return Result.success(new BlogEntityPageBean((int) p.getTotal(),p.getResult()));
    }

    @Override
    public Result searchBlogsList(String searchText, Integer page, Integer pageSize) {//分页模糊搜博客
        PageHelper.startPage(page,pageSize);
        Page<BlogEntity> p = (Page<BlogEntity>) blogMapper.selectBlogsListByText(searchText);
        return Result.success(new BlogEntityPageBean((int) p.getTotal(),p.getResult()));
    }

    @Override
    public Result deleteBlog(Integer blogId, HttpServletRequest httpServletRequest) {//删除
        //对比操作者是否操作自己的帖子
        if(blogMapper.selectOwnerIdByBlogId(blogId).equals(getUserId(httpServletRequest))){//当前贴子拥有者等于用户
            blogMapper.updateRoleTo0ById(blogId);
            return Result.success();
        }
        return Result.error("操作失败，你无权限");
    }

    @Override//点赞或者反对
    public Result like(Integer isLike, Integer isBlog, Integer targetId, HttpServletRequest httpServletRequest) {
        Integer userId=getUserId(httpServletRequest);//当前用户ID
        if(isBlog==1){//博客
            //检查博客是否已经点赞或者反对
            if(blogMapper.selectIsLikeByUserIdAndBlogId(targetId,userId)!=null){//已经点赞或者反对
                if(isLike==1){//点赞
                    blogMapper.updateLikeDisLike(1,targetId,userId);
                }else{//反对
                    blogMapper.updateLikeDisLike(-1,targetId,userId);
                }
            }else{//还未点赞或者反对
                if(isLike==1){//点赞
                    blogMapper.insertLikeDisLikeIntoLikesDislikes(1,targetId,userId);
                }else{//反对
                    blogMapper.insertLikeDisLikeIntoLikesDislikes(-1,targetId,userId);
                }
            }
        }else{//评论

        }
        return Result.success();
    }

    @Override
    public Result cancel(Integer targetId,Integer isBlog, HttpServletRequest httpServletRequest) {//取消点赞和反对
        Integer userId=getUserId(httpServletRequest);//当前用户ID
        if(isBlog==1){//是博客
            blogMapper.updateLikeDisLikeSet0(targetId,userId);
        }else{//是评论

        }
        return Result.success();
    }

    public  boolean isRightUser(Integer id,HttpServletRequest httpServletRequest){//通过id判断是否该用户正确
        //通过httpServletRequest获得令牌中数据Map集合
        String username = blogMapper.selectOwnerNameByOwnerId(id);//通过id得到用户名
        Map<String, Object> map = jsonWebTokenUtil.getMap(httpServletRequest);
        String trueUsername= map.get("username").toString();
        if(username.equals(trueUsername)){
            return  true;
        }
        return false;
    }

    public  boolean isRightUser(String username,HttpServletRequest httpServletRequest){//通过name判断是否该用户正确
        //通过httpServletRequest获得令牌中数据Map集合
        Map<String, Object> map = jsonWebTokenUtil.getMap(httpServletRequest);
        String trueUsername= map.get("username").toString();
        if(username.equals(trueUsername)){
            return  true;
        }
        return false;
    }

    public Integer getUserId(HttpServletRequest httpServletRequest){//通过用户名得到id
        Map<String, Object> map = jsonWebTokenUtil.getMap(httpServletRequest);
        String trueUsername= map.get("username").toString();
        return blogMapper.selectUserIdByUsername(trueUsername);
    }
}
