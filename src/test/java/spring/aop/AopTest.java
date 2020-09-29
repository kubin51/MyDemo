package spring.aop;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.aop.aspect.MyAspect;
import spring.aop.service.PersonService;

/**
 * @author kubin
 * @version V1.0
 * @Package spring.aop
 * @date 2020/9/29 10:42
 * 测试 Spring 的 AOP 功能
 */
public class AopTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonService service = (PersonService) context.getBean("personService");
        service.sayHello();
    }
}
