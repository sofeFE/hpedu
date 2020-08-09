package com.hpedu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {
    @Autowired
    private RedisTemplate redisTemplate ;

    @Test
    public void show(){
        System.out.println( redisTemplate.getKeySerializer());
        System.out.println( redisTemplate.getValueSerializer());
    }
    @Test
    public void testStoreRedis(){
        redisTemplate.opsForValue().set("visitorNum",0);
    }

}
