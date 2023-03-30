package com.app.jimcarry.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.util.Arrays;

@Aspect
@Configuration
@RequiredArgsConstructor
@Slf4j
/**
 * 로그인 세션, 쿠키 검사 Aspect,
 * @apiNote 현재는 세션밖에 검사되지 않음. 쿠키는 적용 안됨.
 * */
public class CheckLoginAspect {

    private final HttpServletRequest request;

    @Before("@annotation(com.app.jimcarry.aspect.annotation.CheckLogin)")
    public void before(JoinPoint joinPoint) {
        HttpSession session = request.getSession();
        String userId = "";
        String userName = "";

        Arrays.asList(request.getCookies()).forEach(e -> log.info("================ 쿠키 : " + e + "================"));

        if (session.getAttribute("userId") != null) {
            userId = String.valueOf(session.getAttribute("userId"));
            userName = String.valueOf(session.getAttribute("userName"));

            log.info("======================= 세션 검사 =======================");
        /*} else if (getCookie(request, "userId") != null) {
            userId = getCookie(request, "userId").getValue();
            userName = getCookie(request, "userName").getValue();

            log.info("======================= 쿠키 검사 =======================");*/
        } else {
            throw new HTTPException(HttpStatus.SC_UNAUTHORIZED);
        }

        if (joinPoint.getArgs()[1] instanceof Model) {
            ((Model) joinPoint.getArgs()[1]).addAttribute("userId", String.valueOf(userId));
            ((Model) joinPoint.getArgs()[1]).addAttribute("userName", userName);
        }
    }

    /*private Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }*/
}
