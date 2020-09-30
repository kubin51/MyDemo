package redis.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * @author kubin
 * @version V1.0
 * @Package redis.utils
 * @date 2020/9/30 10:51
 * properties 配置文件读取工具类
 */
public class PropUtil {
    private static Logger LOG = LoggerFactory.getLogger(PropUtil.class);
    private static final String[] CONFIGS = {"/redis.properties"};
    private static final Properties PROPERTIES = new Properties();

    /**
     * 可载入多个properties文件
     * 相同的属性在最后载入的文件中的值将会覆盖之前的值.
     */
    static {
        try {
            for (String prop : CONFIGS) {
                InputStream resource = PropUtil.class.getResourceAsStream(prop);
                PROPERTIES.load(resource);
            }
        } catch (IOException e) {
            LOG.error("读取配置文件失败");
            e.printStackTrace();
        }
    }

    /**
     * 获得配置项
     *
     * @return 返回配置项
     */
    public static Properties getProperties() {
        return PROPERTIES;
    }

    /**
     * 获得 Boolean 类型的配置值
     *
     * @param key 配置项对应的 key
     * @return 返回配置项对应的布尔值
     */
    public static Boolean getBoolean(String key) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            LOG.info(key + ": 该配置项不存在或为空");
            throw new NoSuchElementException(key + ": 该配置项不存在或为空");
        }
        return Boolean.valueOf(value);
    }

    /**
     * 获得 Double 类型的配置值
     * @param key 配置项对应的 key
     * @return 返回配置项对应的值
     */
    public static Double getDouble(String key) {
        String value = getValue(key);
        if (value == null) {
            LOG.info(key + ": 该配置项不存在或为空");
            throw new NoSuchElementException(key + ": 该配置项不存在或为空");
        }
        return Double.valueOf(value);
    }

    /**
     * 获得 Integer 类型的配置值
     * @param key 配置项对应的 key
     * @return 返回配置项对应的值
     */
    public static Integer getInteger(String key) {
        String value = getValue(key);
        if (value == null) {
            LOG.info(key + ": 该配置项不存在或为空");
            throw new NoSuchElementException(key + ": 该配置项不存在或为空");
        }
        return Integer.valueOf(value);
    }

    /**
     * 获得 String 类型的配置值
     * @param key 配置项对应的 key
     * @return 返回配置项对应的字符串值
     */
    public static String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            LOG.info(key + ": 该配置项不存在或为空");
            throw new NoSuchElementException(key + ": 该配置项不存在或为空");
        }
        return value;
    }

    /**
     * 先从系统参数中获得配置值，没有再从配置文件中获得指定 key 对应的值
     * @param key 配置项对应的 key
     * @return 返回配置项对应的字符串值
     */
    private static String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        if (PROPERTIES.containsKey(key)) {
            return PROPERTIES.getProperty(key);
        }
        return "";
    }
}
