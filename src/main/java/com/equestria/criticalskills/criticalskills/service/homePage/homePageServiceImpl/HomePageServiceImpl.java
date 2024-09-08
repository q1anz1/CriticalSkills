package com.equestria.criticalskills.criticalskills.service.homePage.homePageServiceImpl;

import com.equestria.criticalskills.criticalskills.mapper.homePageMapper.HomePageMapper;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO.UserInfoPageBean;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.homePage.HomePageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.equestria.criticalskills.criticalskills.utils.JudgeValue.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class HomePageServiceImpl implements HomePageService {
    private final HomePageMapper homePageMapper;
    @Override
    public Result pagesearch(String username, Integer gender, Integer ageStart, Integer ageEnd
            , String email, String qq, String phone, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        Page<UserInfo> p = (Page<UserInfo>) homePageMapper.list(username,gender,ageStart,ageEnd,email,qq,phone);
        //判断是否合法
        if(!(judgeAge(ageStart) && judgeAge(ageEnd))){
            log.info("年龄不合法");
            return Result.error("年龄不合法");
        }
        if(!(judgeEmail(email))) {
            log.info("email不合法");
            return Result.error("email不合法");
        }
        if(!(judgeGender(gender))) {
            log.info("性别不合法");
            return Result.error("性别不合法");
        }
        return Result.success(new UserInfoPageBean((int) p.getTotal(),p.getResult()));
    }
    @Override
    public Result randomRecommend() {
        UserInfo userInfo;
        while (true) {
            Random random = new Random();
            int id = random.nextInt(homePageMapper.selectNumberOfUser()) + 1;
            userInfo= homePageMapper.selectUserBasicInfoById(id);
            int role = homePageMapper.selectRoleById(userInfo.getId());
            if(role !=0) {
                break;
            }
        }
        if(userInfo != null){
            return Result.success(userInfo);
        }else {
            return Result.error("未找到任何用户。");
        }

    }
}
