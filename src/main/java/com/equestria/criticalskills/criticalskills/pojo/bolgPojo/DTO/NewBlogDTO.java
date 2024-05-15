package com.equestria.criticalskills.criticalskills.pojo.bolgPojo.DTO;

import lombok.Data;

@Data
public class NewBlogDTO {
    private Integer ownerId;
    private String title;
    private String text;
    private String photoUrl;
    private String videoUrl;
}
