package com.equestria.criticalskills.criticalskills;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.equestria.criticalskills.criticalskills.mapper.userMapper")
public class CriticalSkillsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CriticalSkillsApplication.class, args);
    }

}
