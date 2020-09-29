package spring.aop.service.impl;

import org.springframework.stereotype.Repository;
import spring.aop.service.PersonService;

/**
 * @author kubin
 * @version V1.0
 * @Package spring.aop.service.impl
 * @date 2020/9/29 10:08
 * 目标类
 * Repository 注解将 PersonServiceImpl 注入 spring 容器
 */
@Repository("personService")
public class PersonServiceImpl implements PersonService {
    public void sayHello() {
//        int a = 1/0;
        System.out.println("Hello");
    }

    public void sayHi() {
        System.out.println("Hi");
    }
}
