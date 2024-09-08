package com.equestria.criticalskills.criticalskills.controller.commonController;

import com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO.UserInfoPageBean;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.homePage.HomePageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import static com.equestria.criticalskills.criticalskills.utils.JudgeValue.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomePageController {
    private final HomePageService homePageService;
    @GetMapping ("/main/find_user")
    public Result findUser(@RequestParam String username,Integer gender,Integer ageStart,Integer ageEnd,String email,String qq,String phone
            , @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询,参数：{},{},{},{},{},{},{},页数：{},每页有{}",username, gender,ageStart,ageEnd,email,qq,phone,page,pageSize);
        return homePageService.pagesearch(username,gender,ageStart,ageEnd,email,qq,phone,page,pageSize);
    }
    @GetMapping ("/main")
    public Result randomRecommend(){
        log.info("随机推荐");
        return homePageService.randomRecommend();
    }
}
