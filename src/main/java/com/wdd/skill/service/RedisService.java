package com.wdd.skill.service;

import com.wdd.skill.redis.KeyPrefix;
import com.wdd.skill.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis服务端
 *
 * @author
 * @create 2018-01-20 19:43
 **/
@Service
public class RedisService {
    @Autowired
    private JedisPool jedisPool;


    /**
     * 获取当个对象
     * */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            String  str = jedis.get(realKey);
            T t =  JsonUtil.string2Bean(str, clazz);
            return t;
        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 设置对象
     * */
    public <T> boolean set(KeyPrefix prefix, String key,  T value) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            String str = JsonUtil.bean2String(value);
            if(str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            int seconds =  prefix.expireSeconds();
            if(seconds <= 0) {
                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }





}
