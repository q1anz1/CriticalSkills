package com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@lombok.Data
public class User {
        /**
         * 年龄
         */
        private Long age;
        /**
         * 头像的url地址
         */
        private String avator;
        /**
         * 生日
         */
        @JsonProperty("birth_date") // 指定JSON字段名
        private LocalDate birthDate ;
        /**
         * 城市
         */
        private String city;
        /**
         * 创建时间
         */
        private OffsetDateTime createTime;
        /**
         * 邮箱
         */
        private String email;
        /**
         * 性别
         */
        private Long gender;
        /**
         * 用户id
         */
        private Long id;
        /**
         * 个人介绍
         */
        private String introduction;
        /**
         * 联系电话
         */
        private String phone;
        /**
         * 图片的url集合
         */
        private String photo;
        /**
         * 省份
         */
        private String province;
        /**
         * QQ号
         */
        private String qq;
        /**
         * 更新时间
         */
        private OffsetDateTime updateTime;
        /**
         * 用户名
         */
        private String username;
        /**
         * 视频的url集合
         */
        private String video;

        @JsonProperty("nick_name") // 指定JSON字段名
        private String nickName;
}
