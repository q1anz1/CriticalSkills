package com.equestria.criticalskills.criticalskills.pojo.bolgPojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogEntity {
    private Integer id;
    private String ownerName;
    private Integer ownerId;
    private String title;
    private String text;
    private String photoUrl;
    private String videoUrl;
    private Integer likes=0;
    private Integer dislikes=0;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
