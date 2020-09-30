package redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author kubin
 * @version V1.0
 * @Package redis.utils
 * @date 2020/9/29 16:10
 * Redis 连接池工具类
 */
public class JedisPoolUtil {
    private static final Properties PROPERTIES = new Properties();
    static {
        try {
            PROPERTIES.load(JedisPoolUtil.class.getResourceAsStream("/redis.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jedis getJedis(){
        String host = PROPERTIES.getProperty("host");
        String port = PROPERTIES.getProperty("port");
        JedisPool jedisPool = new JedisPool(host, Integer.parseInt(port));
        return jedisPool.getResource();
    }
}
