package com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SystemMsgDTO {
    private List<String> emails;
    private String msg;
}
