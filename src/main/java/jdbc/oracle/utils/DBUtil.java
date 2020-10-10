package jdbc.oracle.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.utils.PropUtil;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author kubin
 * @version V1.0
 * @Package jdbc.oracle.utils
 * @date 2020/10/9 9:50
 * 数据库连接工具类
 */
public class DBUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DBUtil.class);
    private static DataSource dataSource;
    static {
        try {
            dataSource = DruidDataSourceFactory.createDataSource(PropUtil.getProperties());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * JDBC获得数据库连接对象
     *
     * @return 数据库连接对象
     */
    public static Connection getConnection() {
        try {
            LOG.info("开始加载数据库驱动");
            Class.forName(PropUtil.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            LOG.error("加载数据库驱动失败！");
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(PropUtil.getProperty("url"), PropUtil.getProperty("username"), PropUtil.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从数据库连接池中获得连接对象
     *
     * @return 数据库连接对象
     */
    public static Connection getConnectionPool() {
        try {
            LOG.info("从数据库连接池中获得连接对象");
            return dataSource.getConnection();
        } catch (Exception e) {
            LOG.error("从数据库连接池中获取连接对象失败！");
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 归还数据库连接
     * @param t 要被归还的数据库连接对象
     * @param <T> 数据库连接对象的类型
     */
    public static <T> void releaseResources(T t) {
        if (t != null) {
            try {
                Class<?> aClass = t.getClass();
                Method close = aClass.getMethod("close");
                close.invoke(t);
            } catch (Exception e) {
                LOG.error("归还数据库连接失败！");
                e.printStackTrace();
            }
        }
    }
}
