package util.domain;

import java.util.List;

/**
 * @author kubin
 * @version V1.0
 * @Package util.domain
 * @date 2020/12/28 10:48
 */
public class Teacher {
    private String name;
    private String sex;
    private Integer age;
    private String school;
    private List<String> teachs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<String> getTeachs() {
        return teachs;
    }

    public void setTeachs(List<String> teachs) {
        this.teachs = teachs;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                ", teachs=" + teachs +
                '}';
    }
}
