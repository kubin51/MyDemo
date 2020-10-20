package jdbc.oracle.service;

import jdbc.oracle.pojo.Student;

import java.util.List;

/**
 * @author kubin
 * @version V1.0
 * @Package jdbc.oracle.service
 * @date 2020/10/10 16:05
 */
public interface StudentService {
    /**
     * 新增学生信息
     * @param student 要新增的学生信息
     */
    void addStudents(Student student);

    /**
     * 删除指定ID的学生信息
     * @param id 要删除的学生ID
     */
    void delStudentById(Long id);

    /**
     * 修改指定的学生信息
     * @param student 要修改的学生信息
     */
    void editStudentById(Student student);

    /**
     * 获取学生信息集合
     * @return 返回学生信息集合
     */
    List<Student> getStudents();
}
