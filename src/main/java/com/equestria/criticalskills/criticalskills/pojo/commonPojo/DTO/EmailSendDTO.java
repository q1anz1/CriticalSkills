package com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO;

import lombok.Data;

import java.util.List;


@Data
public class EmailSendDTO {

    private String username;
    private String email;
    private String subject;
    private String content;
    private String verification;
}
