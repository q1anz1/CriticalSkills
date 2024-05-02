package com.equestria.criticalskills.criticalskills;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component   //一定不要忘记将处理器加到IOC容器中！
public class hander implements MetaObjectHandler {

    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill.....");
        //setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        //第一个参数 字段名
        //第二个参数 值
        //第三个参数 metaObject
//        this.setFieldValByName("createTime",new Date(),metaObject);
//        this.setFieldValByName("updateTime",new Date(),metaObject);
        // 上面的方法是旧版本，新版本的是使用下面的strictInsertFill方法
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

    }

    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill.....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}

