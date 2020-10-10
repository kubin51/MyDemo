package jdbc.oracle.pojo;

import java.util.Date;

/**
 * @author kubin
 * @version V1.0
 * @Package jdbc.oracle.pojo
 * @date 2020/10/9 15:41
 */
public class Student {
    private String name;
    private Integer age;
    private String sex;
    private Date birthday;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
