package com.app.jimcarry.aspect;

import com.app.jimcarry.controller.MainController;
import com.app.jimcarry.controller.MypageController;
import com.app.jimcarry.controller.PayController;
import com.app.jimcarry.controller.StorageController;
import com.app.jimcarry.exception.AuthenticationFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import java.util.NoSuchElementException;

/**
 * MypageController에서 발생하는 예외 처리를 담당하는 ExceptionHandler 클래스.
 *
 * @see com.app.jimcarry.intercepter.AuthInterceptor
 * @see com.app.jimcarry.config.WebMvcConfig
 */
@ControllerAdvice(basePackageClasses = {MypageController.class, StorageController.class, PayController.class})
public class AuthenticationFailureExceptionHandler {

    @ExceptionHandler(AuthenticationFailureException.class)
    public RedirectView exceptionAdvice(Exception e) {
        return new RedirectView("/user/login");
    }

}
