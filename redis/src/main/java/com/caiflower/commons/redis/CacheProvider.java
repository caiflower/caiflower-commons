package com.caiflower.commons.redis;

import java.util.concurrent.TimeUnit;

/**
 * @author: lijinlong
 * @date: 2022/8/19 9:09
 */
public interface CacheProvider {

    /**
     * 是否存在key
     *
     * @param key key
     * @return true or false
     */
    boolean exist(String key);

    /**
     * 设置对象（无超时）
     *
     * @param key   key
     * @param value value
     */
    void set(String key, Object value);

    void expire(String key, Object value, Long expireTime);

    /**
     * 带有超时的设置对象
     *
     * @param key        key
     * @param value      value
     * @param expireTime 超时时间
     * @param timeUnit   时间单位
     */
    void expire(String key, Object value, Long expireTime, TimeUnit timeUnit);

    /**
     * 获取对象
     *
     * @return Object
     */
    Object get(String key);

    /**
     * 根据类型获取对象
     */
    <T> T get(String key, Class<T> type);

    /**
     * 哈希值设置
     *
     * @param hashKey hash表key
     * @param key     key
     * @param value   value值
     */
    void hset(String hashKey, String key, String value);

    void hExpire(String hashKey, String key, String value, Long expireTime);

    /**
     * 带有超时的设置hash值
     *
     * @param hashKey    hashKey
     * @param key        key
     * @param value      value
     * @param expireTime 超时时间
     * @param timeUnit   时间单位
     */
    void hExpire(String hashKey, String key, String value, Long expireTime, TimeUnit timeUnit);

    /**
     * 哈希值获取
     *
     * @return Object
     */
    Object hget(String hashKey, String key);

    /**
     * 根据类型获取hash值
     */
    <T> T hget(String hashKey, String key, Class<T> type);

    /**
     * 集合设置
     *
     * @param key   key
     * @param value value
     */
    void sadd(String key, Object value);

    /**
     * 获取集合成员的个数
     *
     * @param key key
     * @return 个数
     */
    long scard(String key);

    /**
     * 判断集合中是否存在制定成员
     *
     * @param key    key
     * @param member 成员
     * @return true or false
     */
    boolean isContains(String key, Object member);

    /**
     * 设置超时时间
     * @param key key
     * @param expireTime 超时时间
     * @param timeUnit 时间单位
     */
    boolean expire(String key, Long expireTime, TimeUnit timeUnit);
}
