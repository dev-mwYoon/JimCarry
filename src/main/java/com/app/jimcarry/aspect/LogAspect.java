package com.app.jimcarry.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 *
 * 로그 출력 Aspect
 *
 * @author 강민구
 * @since 2023/03/21
 * */
@Aspect
@Configuration
@Slf4j
public class LogAspect {

    @Before("@annotation(com.app.jimcarry.aspect.annotation.LogStatus)")
    public void beforeStart(JoinPoint joinPoint){
        log.info("......method : " + joinPoint.getSignature().getName());
        Arrays.asList(joinPoint.getArgs()).stream().map(String::valueOf).forEach(e -> log.info("......args : " + e));
    }
}
