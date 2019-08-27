package com.ph.miaosha;

import com.ph.miaosha.redis.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiaoshaApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    RedisConfig redisConfig;
    @Autowired
    JedisPool jedisPool;
    @Test
    public void redisTest(){
        if (redisConfig==null)
            System.out.println("redisConfig为空。/");
        else
            System.out.println("Test"+redisConfig);
        if (jedisPool!=null){
            System.out.println("jedis不为空");
        }
    }
//    @Autowired
//    JedisPoolConfig jedisPoolConfig;
//    @Test
//    public void jedisPool(){
//        if (jedisPoolConfig==null){
//            System.out.println("jedisPool为空");
//        }
//    }

}
