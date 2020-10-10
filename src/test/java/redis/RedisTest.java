package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.utils.RedisUtil;

import java.sql.DriverManager;


/**
 * @author kubin
 * @version V1.0
 * @Package redis
 * @date 2020/9/29 16:35
 */
public class RedisTest {
    @Test
    public void test(){
        Jedis jedis = RedisUtil.getJedis();
        String test = jedis.get("t1");
        System.out.println(test);
        Jedis jedis1 = RedisUtil.getJedis();
        jedis1.set("test","kubin");
        String test1 = jedis1.get("test");
        System.out.println(test1);
    }
}
