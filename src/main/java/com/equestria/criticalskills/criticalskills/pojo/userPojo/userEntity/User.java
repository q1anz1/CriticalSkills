package com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    /**
     * user
     */

        /**
         * id
         */
        @TableId(type = IdType.AUTO)
        private long id;
        /**
         * 用户名
         */
        private String username;
        /**
         * 密码
         */
        private String password;
        /**
         * 姓名
         */
        private String name;
        /**
         * 性别，男1，女2
         */
        private Short gender;

        /**
         * 生日，2000-1-1
         */
        private LocalDate birthday;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 图片
         */
        private String image;
        /**
         * 视频
         */
        private String video;
        /**
         * 介绍
         */
        private String introduce;
        /**
         * 电话，11位
         */
        private String phone;
        /**
         * 省
         */
        private String province;
        /**
         * 市
         */
        private String city;




}
