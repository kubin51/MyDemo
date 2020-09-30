package redis.service.impl;

import redis.service.CacheService;

/**
 * @author kubin
 * @version V1.0
 * @Package redis.service.impl
 * @date 2020/9/29 15:59
 * 缓存服务实现类
 */
public class CacheServiceImpl implements CacheService {
    public void putObject(String key, Object value) {

    }

    public void putObject(String key, Object value, int expiration) {

    }

    public Object pullObject(String key) {
        return null;
    }

    public Boolean expire(String key, int expireSecond) {
        return null;
    }

    public Long getExpireSecond(String key) {
        return null;
    }

    public Boolean delObject(String key) {
        return null;
    }

    public void clearObject() {

    }
}
