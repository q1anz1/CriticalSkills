package com.equestria.criticalskills.criticalskills.service.homePage.homePageServiceImpl;

import com.equestria.criticalskills.criticalskills.mapper.homePageMapper.HomePageMapper;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO.PageBean;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.Account;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.service.homePage.HomePageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;


@Slf4j
@Service
@RequiredArgsConstructor
public class HomePageServiceImpl implements HomePageService {
    private final HomePageMapper homePageMapper;
    @Override
    public PageBean pagesearch(String username, Integer gender, Integer ageStart,Integer ageEnd
            , String email, String qq, String phone, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        Page<UserInfo> p = (Page<UserInfo>) homePageMapper.list(username,gender,ageStart,ageEnd,email,qq,phone);
        return new PageBean((int) p.getTotal(),p.getResult());
    }
    @Override
    public UserInfo randomRecommend() {
        UserInfo UserInfo;
        while (true) {
            Random random = new Random();
            int id = random.nextInt(homePageMapper.selectNumberOfUser()) + 1;
            int role = homePageMapper.selectRoleById(id);
            if(role !=0) {
                UserInfo= homePageMapper.selectUserInfoById(id);
                break;
            }
        }
        return UserInfo;
    }
}
