package com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SelectUserDTO {
    private String username;
    private Integer age;
    private Integer gender;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String qq;
    private String avator;
    private String province;
    private String city;
    private Integer currentPage;
    private Integer pageSize;
    private Integer startIndex;
}
