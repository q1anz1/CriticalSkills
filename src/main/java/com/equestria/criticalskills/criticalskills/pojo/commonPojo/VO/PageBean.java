package com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO;

import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserBasicInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Integer count;
    private List<UserBasicInfo> userList;
}
