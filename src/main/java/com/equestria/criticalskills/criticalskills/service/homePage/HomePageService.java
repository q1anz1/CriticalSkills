package com.equestria.criticalskills.criticalskills.service.homePage;

import com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO.PageBean;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import org.springframework.web.bind.annotation.RequestParam;

public interface HomePageService {
    PageBean pagesearch(String username, Integer gender, Integer ageStart,Integer ageEnd
            , String email, String qq, String phone, Integer page, Integer pageSize);
    UserInfo randomRecommend();

}
