package jdbc.oracle.service.impl;

import jdbc.oracle.pojo.Student;
import jdbc.oracle.service.StudentService;
import jdbc.oracle.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kubin
 * @version V1.0
 * @Package jdbc.oracle.service.impl
 * @date 2020/10/10 16:07
 */
public class StudentServiceImpl implements StudentService {

    public void addStudents(Student student) {
        Connection conn = null;

        try {
            conn = DBUtil.getConnectionPool();
            PreparedStatement preparedStatement = null;
            preparedStatement = conn.prepareStatement("select id_student.nextval as id from dual");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                long id = resultSet.getLong("id");
                PreparedStatement statement = conn.prepareStatement("insert into student (name,age,sex,birthday,id) values(?,?,?,?,?)");
                statement.setString(1,student.getName());
                statement.setInt(2,student.getAge());
                statement.setString(3,student.getSex());
                statement.setDate(4,student.getBirthday());
                statement.setLong(5,id);
                int i = statement.executeUpdate();
                if(i==1){
                    System.out.println("新增成功");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delStudentById(Long id) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnectionPool();
            if(conn != null){
                PreparedStatement statement = conn.prepareStatement("delete from student where id=?");
                statement.setLong(1,id);
                int i = statement.executeUpdate();
                if(i==1){
                    System.out.println("删除成功");
                }
            }else{
               throw new RuntimeException("从连接池获取连接失败！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void editStudentById(Student student) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnectionPool();
            if(conn != null){
                PreparedStatement statement = conn.prepareStatement("update student set name=?,age=?,sex=?,birthday=? where id=?");
                statement.setString(1,student.getName());
                statement.setInt(2,student.getAge());
                statement.setString(3,student.getSex());
                statement.setDate(4,student.getBirthday());
                statement.setLong(5,student.getId());
                int i = statement.executeUpdate();
                if(i==1){
                    System.out.println("修改成功");
                }
            }else{
                throw new RuntimeException("从连接池获取连接失败！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<Student> getStudents() {
        List<Student> list = new ArrayList<Student>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnectionPool();
            if(conn != null){
                PreparedStatement statement = conn.prepareStatement("select name,age,sex,birthday,id from student");
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    Student student = new Student();
                    student.setName(resultSet.getString("name"));
                    student.setAge(resultSet.getInt("age"));
                    student.setSex(resultSet.getString("age"));
                    student.setBirthday(resultSet.getDate("birthday"));
                    student.setId(resultSet.getLong("id"));
                    list.add(student);
                }
            }else{
                throw new RuntimeException("从连接池获取连接失败！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }
}
