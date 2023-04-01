package com.app.jimcarry.intercepter;

import com.app.jimcarry.controller.MypageController;
import com.app.jimcarry.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 마이페이지 컨트롤러에 가기 전, 로그인 여부를 검사하는 인터셉터.
 * 세션과 쿠키 모두 조회 후, 쿠키만 있다면 세션에 그 값을 담고,
 * 둘 다 없다면 예외를 발생시킨다.
 *
 * 해당 예외는 MypageControllerAdvice가 담당하게 된다.
 *
 * @see com.app.jimcarry.aspect.MypageControllerAdvice
 * */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /* 컨트롤러 요청이 아닌, 정적 리소스 요청 DefaultServletHttpRequestHandler 요청일 경우 */
        if(!(handler instanceof  HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handlerMethod.getBean() instanceof MypageController) {
            log.info("==========SESSION AND COOKIE========");
            if(request.getSession().getAttribute("user") == null && checkCookie(request)) {
                /* 컨트롤러에서 받을 예외, 추후 커스텀 예외로 변경할 수 있음. */
                throw new IllegalArgumentException();
            }
        }

        return true;
    }

    /**
     * 세션에 유저정보가 없을 경우 cookie 조회 조회되면 false, 조회가 되지 않으면 true 리턴
     *
     * @param request 컨트롤러로 가기 전 request 객체
     * */
    private boolean checkCookie(HttpServletRequest request) {
        /* 현재 세션에 userVO 객체를 담는 형식으로 되어있어서 new UserVO()로 세션에 담아야함 */
        UserVO userVO = new UserVO();
        boolean checkUserId = false;
        boolean checkUserName = false;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("userId")){
                checkUserId = true;
                userVO.setUserId(Long.valueOf(cookie.getValue()));
            }
            else if(cookie.getName().equals("userName")){
                checkUserName = true;
                userVO.setUserName(cookie.getValue());
            }
        }

        /* 회원 정보 쿠키가 모두 있을 경우 */
        if(checkUserId && checkUserName) {
            request.getSession().setAttribute("user", userVO);
            return false;
        }

        /* 회원 정보 쿠키가 없을 경우 */
        return true;
    }
}
