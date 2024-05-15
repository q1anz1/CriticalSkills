package com.equestria.criticalskills.criticalskills.pojo.commonPojo.VO;

import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.BlogEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogEntityPageBean {
    private Integer count;
    private List<BlogEntity> userList;
}
