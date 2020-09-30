package redis.utils;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author kubin
 * @version V1.0
 * @Package redis.utils
 * @date 2020/9/30 10:20
 */
public class RedisUtil {

    protected static Logger logger = Logger.getLogger(RedisUtil.class);
    private static JedisPool jedisPool = null;

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }

    private static void initialPool(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(PropUtil.getInteger("max_active"));
            config.setMaxIdle(PropUtil.getInteger("max_idle"));
            config.setMaxWaitMillis(PropUtil.getInteger("max_wait"));
            config.setTestOnBorrow(PropUtil.getBoolean("test_on_borrow"));
            jedisPool = new JedisPool(config, PropUtil.getProperty("host"), PropUtil.getInteger("port"), PropUtil.getInteger("timeout"));
        } catch (Exception e) {
            logger.error("create JedisPool error : "+e);
        }
    }


    /**
     * 同步获取Jedis实例
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            logger.error("get jedis error : "+e);
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return jedis;
    }
}
