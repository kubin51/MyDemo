package spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author kubin
 * @version V1.0
 * @Package spring.aop.aspect
 * @date 2020/9/29 10:23
 * 切面类
 */
@Aspect
@Component("myAspect")
public class MyAspect {
    @Pointcut("execution(* spring.aop..*.*(..))")
    public void a(){

    }
    @Before("a()")
    public void doBefore(){
        System.out.println("前置功能");
    }
    @AfterReturning("a()")
    public void doAfterReturning(){
        System.out.println("后置功能");
    }
    @Around("a()")
    public Object doAround(ProceedingJoinPoint pjp){
        Object proceed = null;
        try {
            System.out.println("环绕前功能");
            proceed = pjp.proceed();
            System.out.println("环绕后功能");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

    /**
     * 同 @After("a()")
     */
    @After("execution(* spring.aop..*.*(..))")
    public void doAfter(){
        System.out.println("最终功能");
    }

    /**
     * 目标方法中如果可能发生异常，需抛出该异常，这样 @AfterThrowing 才能捕获该异常
     * @param e 异常信息
     */
    @AfterThrowing(value = "a()",throwing = "e")
    public void doAfterThrowing(Throwable e){
        System.out.println("抛出了异常"+e.getMessage());
    }
}
