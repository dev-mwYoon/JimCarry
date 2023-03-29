package com.app.jimcarry.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.util.Optional;

@Aspect
@Configuration
@Slf4j
public class CheckLoginAspect {

    @Before("@annotation(com.app.jimcarry.aspect.annotation.CheckLogin)")
    public void before(JoinPoint joinPoint) {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        Object userId = session.getAttribute("userId");
        String userName = "";
        Optional.ofNullable(userId).orElseThrow(() -> new HTTPException(HttpStatus.SC_UNAUTHORIZED));
        userName = String.valueOf(session.getAttribute("userName"));

        if (joinPoint.getArgs()[1] instanceof Model) {
            ((Model) joinPoint.getArgs()[1]).addAttribute("userId", String.valueOf(userId));
            ((Model) joinPoint.getArgs()[1]).addAttribute("userName", userName);
        }
    }
}
