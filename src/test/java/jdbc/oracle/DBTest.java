package jdbc.oracle;

import jdbc.oracle.utils.DBUtil;
import org.junit.Test;
import java.sql.*;

/**
 * @author kubin
 * @version V1.0
 * @Package jdbc.oracle
 * @date 2020/10/9 10:23
 */
public class DBTest {
    @Test
    public void insertTest(){
        try {
            Connection conn = DBUtil.getConnectionPool();
            PreparedStatement preparedStatement = conn.prepareStatement("select id_student.nextval as id from dual");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
//                long id = resultSet.getLong(1);
                long id = resultSet.getLong("id");
                PreparedStatement statement1 = conn.prepareStatement("insert into student (id,name,age,sex,birthday) values(?,?,?,?,?)");
                statement1.setLong(1,id);
                statement1.setString(2,"fangbin");
                statement1.setInt(3,26);
                statement1.setString(4,"M");
                System.out.println(System.currentTimeMillis());
                statement1.setDate(5,new Date(System.currentTimeMillis()));
                statement1.executeUpdate();
                DBUtil.releaseResources(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void deleteTest(){
        try {
            Connection conn = DBUtil.getConnectionPool();
            PreparedStatement preparedStatement = conn.prepareStatement("delete from student where id=?");
            preparedStatement.setLong(1,10000026);
            preparedStatement.executeUpdate();
            DBUtil.releaseResources(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void updateTest(){
        try {
            Connection conn = DBUtil.getConnectionPool();
            PreparedStatement preparedStatement = conn.prepareStatement("update student set name='kukuxiaoshuaibin' where id=?");
            preparedStatement.setLong(1,10000029);
            preparedStatement.executeUpdate();
            DBUtil.releaseResources(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void selectTest(){
        try {
            Connection conn = DBUtil.getConnectionPool();
            PreparedStatement preparedStatement = conn.prepareStatement("select name,age,sex,birthday,id from student");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                long id = resultSet.getLong("id");
                System.out.println(id);
            }
            DBUtil.releaseResources(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
