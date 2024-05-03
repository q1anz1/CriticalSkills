/*
package com.equestria.criticalskills.criticalskills.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfig {

        @Bean
        public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
            RedisTemplate redisTemplate = new RedisTemplate();
            //设置redis的连接工厂对象
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            //设置redis key的序列化器
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            return redisTemplate;
        }

}


*/
package com.equestria.criticalskills.criticalskills.config;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.equestria.criticalskills.criticalskills.mapper")
public class RedisConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}