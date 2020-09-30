package redis.service;

/**
 * @author kubin
 * @version V1.0
 * @Package redis.service
 * @date 2020/9/29 15:41
 * 缓存服务接口
 */
public interface CacheService {
    /**
     * 将对象放入缓存中
     * @param key 缓存的key
     * @param value 缓存的值
     */
    void putObject(String key,Object value);

    /**
     * 将对象放入缓存并设置过期时间
     * @param key 缓存的key
     * @param value 缓存的值
     * @param expiration 缓存过期时间
     */
    void putObject(String key,Object value,int expiration);

    /**
     * 通过缓存key获得缓存的值
     * @param key 缓存的key
     * @return 缓存的值
     */
    Object pullObject(String key);

    /**
     * 给缓存对象设置过期时间
     * @param key 缓存对象对应的key
     * @param expireSecond 过期时间
     * @return 返回是否设置成功标识
     */
    Boolean expire(String key,int expireSecond);

    /**
     * 获得缓存对象的过期时间
     * @param key 缓存对象对应的key
     * @return 缓存对象的过期时间，如果对象不存在返回-2，如果对象没有设置过期时间返回-1，否则返回实际过期时间
     */
    Long getExpireSecond(String key);

    /**
     * 通过key清除该key对应的缓存
     * @param key 缓存的key
     * @return 返回是否删除成功的标识
     */
    Boolean delObject(String key);

    /**
     * 清除所有缓存
     */
    void clearObject();
}
