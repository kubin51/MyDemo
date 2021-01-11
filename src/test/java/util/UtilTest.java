package util;

import org.junit.Test;
import util.domain.Student;

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
    @Test
    public void testExcelToList() {
        String[] heads = new String[]{"ID;id", "姓名;name", "性别;sex", "年龄;age", "生日;birthday", "户籍;homeCity", "爱好;hobby"};
        List<Map<Object, Object>> lists = ExcelUtils.excelToListMap("out_file\\学生信息.xlsx", 0, heads);

        System.out.println(lists);
    }

    @Test
    public void testListToExcel() {
        String inPath = "template\\学生模板.xlsx";
        String outPath = "out_file\\学生信息.xlsx";
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
}
