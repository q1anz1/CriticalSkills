package com.equestria.criticalskills.criticalskills.service.homePage;

import com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO.UserInfoPageBean;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;

public interface HomePageService {
    UserInfoPageBean pagesearch(String username, Integer gender, Integer ageStart, Integer ageEnd
            , String email, String qq, String phone, Integer page, Integer pageSize);
    UserInfo randomRecommend();

}
