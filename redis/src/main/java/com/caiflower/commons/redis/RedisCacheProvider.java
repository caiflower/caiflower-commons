package com.caiflower.commons.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: lijinlong
 * @date: 2022/8/19 9:28
 */
@Component
@ConfigurationProperties("spring.redis.expire")
public class RedisCacheProvider implements CacheProvider {

    private String active;

    private Long expireTime;

    private TimeUnit timeUnit;

    public void setActive(String active) {
        this.active = active;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void set(String key, Object value) {
        if ("true".equals(active)) {
            expire(key, value, expireTime, timeUnit);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    @Override
    public void expire(String key, Object value, Long expireTime) {
        expire(key, value, expireTime, timeUnit);
    }

    @Override
    public void expire(String key, Object value, Long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    @Override
    public Object get(String key) {
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>("return redis.call('GET',KEYS[1])", Object.class);
        return redisTemplate.execute(redisScript, Collections.singletonList(key));
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        DefaultRedisScript<T> redisScript = new DefaultRedisScript<>("return redis.call('GET',KEYS[1])", type);
        return redisTemplate.execute(redisScript, Collections.singletonList(key));
    }

    @Override
    public void hset(String hashKey, String key, String value) {
        if ("true".equals(active)) {
            hExpire(hashKey, key, value, expireTime, timeUnit);
        } else {
            redisTemplate.opsForHash().put(hashKey, key, value);
        }
    }

    @Override
    public void hExpire(String hashKey, String key, String value, Long expireTime) {
        hExpire(hashKey, key, value, expireTime, timeUnit);
    }

    @Override
    public void hExpire(String hashKey, String key, String value, Long expireTime, TimeUnit timeUnit) {
        redisTemplate.expire(hashKey, expireTime, timeUnit);
        redisTemplate.opsForHash().put(hashKey, key, value);
    }

    @Override
    public Object hget(String hashKey, String key) {
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>("return redis.call('HGET', KEYS[1], KEYS[2])", Object.class);
        List<String> keys = new ArrayList<>();
        keys.add(hashKey);
        keys.add(key);
        return redisTemplate.execute(redisScript, Collections.unmodifiableList(keys));
    }

    @Override
    public <T> T hget(String hashKey, String key, Class<T> type) {
        DefaultRedisScript<T> redisScript = new DefaultRedisScript<>("return redis.call('HGET', KEYS[1], KEYS[2])", type);
        List<String> keys = new ArrayList<>();
        keys.add(hashKey);
        keys.add(key);
        return redisTemplate.execute(redisScript, Collections.unmodifiableList(keys));
    }

    @Override
    public void sadd(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public long scard(String key) {
        return redisTemplate.opsForSet().size(key).longValue();
    }

    @Override
    public boolean isContains(String key, Object member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    @Override
    public boolean expire(String key, Long expireTime, TimeUnit timeUnit) {
        return redisTemplate.expire(key, expireTime, timeUnit);
    }
}
