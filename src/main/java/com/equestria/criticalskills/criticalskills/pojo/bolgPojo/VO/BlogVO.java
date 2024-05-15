package com.equestria.criticalskills.criticalskills.pojo.bolgPojo.VO;

import com.equestria.criticalskills.criticalskills.pojo.bolgPojo.BlogEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogVO {
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

    private Integer isLike;



    public BlogVO setBlogVO(BlogEntity blogEntity){
        this.id=blogEntity.getId();
        this.ownerName=blogEntity.getOwnerName();
        this.ownerId=blogEntity.getOwnerId();
        this.title=blogEntity.getTitle();
        this.text=blogEntity.getText();
        this.photoUrl=blogEntity.getPhotoUrl();
        this.videoUrl=blogEntity.getVideoUrl();
        this.likes=blogEntity.getLikes();
        this.createTime=blogEntity.getCreateTime();
        this.updateTime=blogEntity.getUpdateTime();
        return this;
    }
}
