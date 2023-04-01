package com.app.jimcarry.aspect;

import com.app.jimcarry.controller.MypageController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import java.util.NoSuchElementException;
/**
 * MypageController에서 발생하는 예외 처리를 담당하는 Advice 클래스.
 *
 * @see com.app.jimcarry.intercepter.AuthInterceptor
 * @see com.app.jimcarry.config.WebMvcConfig
 * */
@ControllerAdvice(basePackageClasses = MypageController.class)
public class MypageControllerAdvice {

    @ExceptionHandler(Exception.class)
    public RedirectView exceptionAdvice(Exception e){

        if (e instanceof NoSuchElementException){
            return new RedirectView("/user/login");
        }

        if (e instanceof IllegalArgumentException){
            return new RedirectView("/user/login");
        }

        else return new RedirectView("/main");
    }

}
