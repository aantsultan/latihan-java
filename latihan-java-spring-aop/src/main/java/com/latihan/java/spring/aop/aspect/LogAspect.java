package com.latihan.java.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("target(com.latihan.java.spring.aop.service.HelloService)")
    public void helloServiceMethod() { // eksekusi semua method yang ada di HelloService
    }

    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod(JoinPoint joinPoint) { // eksekusi sebelum helloServiceMethod() dieksekusi
        String uuid = UUID.randomUUID().toString();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Before {}.{}() {}", className, methodName, uuid);
    }

    @Around("helloServiceMethod()")
    public Object aroundHelloServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            log.info("Around Before {}.{}() {}", className, methodName, uuid);
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable throwable) {
            log.info("Around Error {}.{}() {}", className, methodName, uuid);
            throw throwable;
        } finally {
            log.info("Around Finally {}.{}() {}", className, methodName, uuid);
        }
    }

    @Pointcut("execution(* com.latihan.java.spring.aop.service.HelloService.*(java.lang.String))")
    public void pointCutHelloServiceStringParam() {

    }

//    @Before("pointCutHelloServiceStringParam()")
//    public void logStringParameter(JoinPoint joinPoint) {
//        String value = (String) joinPoint.getArgs()[0];
//        log.info("Execute method with parameter : {}", value);
//    }

    @Before("pointCutHelloServiceStringParam() && args(name)")
    public void logStringParameter(String name){
        log.info("Execute method with parameter : {}", name);
    }

    @Pointcut("execution(* com.latihan.java.spring.aop.service.*.*(..))")
    public void pointcutServicePackage(){

    }

    @Pointcut("bean(*Service)")
    public void pointcutServiceBean(){

    }

    @Pointcut("execution(public * *(..))")
    public void pointcutPublicMethod(){

    }

    @Pointcut("pointcutServicePackage() && pointcutServiceBean() && pointcutPublicMethod()")
    public void publicMethodForService(){

    }

    @Before("publicMethodForService()")
    public void allPublicServiceMethod(){
        log.info("Log for all public service method");
    }

}
