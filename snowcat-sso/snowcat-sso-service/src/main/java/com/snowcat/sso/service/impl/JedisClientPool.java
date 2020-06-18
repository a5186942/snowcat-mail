package com.snowcat.sso.service.impl;

import com.snowcat.sso.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisClientPool implements JedisClient {


    @Autowired
    JedisPool jedisPool;
    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String set = jedis.set(key, value);
        jedis.close();

        return set;
    }

    @Override
    public String get(String key) {
        Jedis resource = jedisPool.getResource();
        String s = resource.get(key);
        resource.close();
        return s;

    }

    @Override
    public Boolean exists(String key) {

        Jedis resource = jedisPool.getResource();
        Boolean exists = resource.exists(key);
        resource.close();
        return exists;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis resource = jedisPool.getResource();
        Long expire = resource.expire(key, seconds);
        resource.close();
        return expire;

    }

    @Override
    public Long ttl(String key) {
        Jedis resource = jedisPool.getResource();
        Long ttl = resource.ttl(key);
        resource.close();
        return ttl;

    }

    @Override
    public Long incr(String key) {
        Jedis resource = jedisPool.getResource();
        Long incr = resource.incr(key);
        resource.close();
        return incr;

    }

    @Override
    public Long hset(String key, String field, String value) {

        Jedis resource = jedisPool.getResource();
        Long hset = resource.hset(key, field, value);
        resource.close();
        return hset;
    }

    @Override
    public String hget(String key, String field) {

        Jedis resource = jedisPool.getResource();
        String hget = resource.hget(key, field);
        resource.close();
        return hget;
    }

    @Override
    public Long hdel(String key, String... field) {

        Jedis resource = jedisPool.getResource();
        Long hdel = resource.hdel(key, field);
        resource.close();
        return hdel;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long del = jedis.del(key);
        jedis.close();;
        return del;

    }
}
