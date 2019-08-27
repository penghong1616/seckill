package com.ph.miaosha.redis;
/**
 *
 */

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService{
    @Autowired
    JedisPool jedisPool;

    @SuppressWarnings("unchecked")
    public <T> T get( KeyPrefix keyprefix,String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //对key增加前缀，即可用于分类，也避免key重复
            String realKey =keyprefix.getPrefix()+key;
            String str = jedis.get(realKey);
            if (StringUtils.isEmpty(str)){
                return null;
            }
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }

    }
    public<T> boolean set(KeyPrefix keyprefix,String key,T value){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            String str=beanToString(value);
            if (str==null||str.length()==0)
                return false;
            String realKey=keyprefix.getPrefix()+key;
            int seconds=keyprefix.getExpireSecond();
            if (seconds<=0){
                jedis.set(realKey,str);
            }else{
                jedis.setex(realKey,seconds,str);
            }

            return true;
        }finally {
            returnToPool(jedis);
        }

    }
    public<T> boolean exists(KeyPrefix keyprefix,String key){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();

            String realKey=keyprefix.getPrefix()+key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }

    }
    public boolean delete(KeyPrefix keyprefix,String key){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();

            String realKey=keyprefix.getPrefix()+key;
            Long  id=jedis.del(realKey);
            return id>0;
        }finally {
            returnToPool(jedis);
        }

    }
    public<T> Long incr(KeyPrefix keyprefix,String key){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();

            String realKey=keyprefix.getPrefix()+key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }

    }
    public<T> Long decr(KeyPrefix keyprefix,String key){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();

            String realKey=keyprefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }

    }

    private <T> String beanToString(T value) {
        if (value==null)
            return null;
        Class<?> clazz=value.getClass();
        if (clazz==int.class||clazz==Integer.class){
            return ""+value;
        }else if (clazz==String.class){
            return (String)value;
        }else if (clazz==long.class||clazz==Long.class){
            return ""+value;
        } else{
            return JSON.toJSONString(value);
        }

    }
    @SuppressWarnings("unchecked")
    private static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else {
            System.out.println("str:"+str);
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }


    private  void returnToPool(Jedis jedis){
        if (jedis!=null)
            jedis.close();
    }
}
