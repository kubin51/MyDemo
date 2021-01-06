package util;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具测试类
 *
 * @author kubin
 * @version V1.0
 * @Package util
 * @date 2020/12/28 10:33
 */
public class UtilTest {
    /*@Test
    public void testExcelToList() {
        String[] heads = new String[]{"ID;id", "姓名;name", "性别;sex", "年龄;age", "生日;birthday", "户籍;homeCity", "爱好;hobby"};
        List<Map<Object, Object>> lists = ExcelUtils.excelToListMap("C:\\Users\\lenovo\\Desktop\\学生表.xlsx", 0, heads);

        System.out.println(lists);
    }

    @Test
    public void testListToExcel() {
        String inPath = "C:\\Users\\lenovo\\Desktop\\模板.xlsx";
        String outPath = "C:\\Users\\lenovo\\Desktop\\aa.xlsx";
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Student student = new Student();
            student.setAge(20 + i);
            student.setHobby(new ArrayList<String>(Arrays.asList("旅游" + i)));
            student.setHomeCity("安徽" + i);
            student.setBirthday(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            student.setSex(i+"");
            student.setName("lala"+i);
            student.setId(Long.parseLong(i+""));
            list.add(student);
        }
        map.put("list",list);
        ExcelUtils.listToExcel(map,inPath,outPath);
    }
    @Test
    public void test(){
        String format = String.format("%-20s", "");
        String format1 = String.format("%-20s", "17729907597");
        System.out.println(format+format1);
//        String format1 = String.format("%20s", "17729907597");
//        System.out.println(format1);
    }*/

    //测试文本内容对齐
    class Student{
        private String name;
        private String age;
        private String age2;
        private String age3;
        private String age4;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAge2() {
            return age2;
        }

        public void setAge2(String age2) {
            this.age2 = age2;
        }

        public String getAge3() {
            return age3;
        }

        public void setAge3(String age3) {
            this.age3 = age3;
        }

        public String getAge4() {
            return age4;
        }

        public void setAge4(String age4) {
            this.age4 = age4;
        }
    }
    @Test
    public  void test3() {
        List<Student> students = new ArrayList<>();
        Student student2 = new Student();
        student2.setName(String.format("%-20s","successphone"));
        student2.setAge(String.format("%-20s","successphone"));
        student2.setAge2(String.format("%-20s","successphone"));
        student2.setAge3(String.format("%-20s","successphone"));
        student2.setAge4(String.format("%-20s","successphone"));
        students.add(student2);
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setName(String.format("%-20s","17729907597"));
            student.setAge(String.format("%-20s",""));
            student.setAge2(String.format("%-20s",""));
            student.setAge3(String.format("%-20s",""));
            student.setAge4(String.format("%-20s","17729907597"));
            students.add(student);
        }
        String filePath = "C:\\Users\\lenovo\\Desktop\\Student2.txt";
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "gbk"))) {
            // 遍历数组拿到数组中元素的属性按要求写入到文件中
            for (Student s : students) {
                out.write(s.getName());
                out.write(s.getAge());
                out.write(s.getAge2());
                out.write(s.getAge3());
                out.write(s.getAge4() + "\r\n");
            }

        } catch (IOException e) {

        }

    }
}
