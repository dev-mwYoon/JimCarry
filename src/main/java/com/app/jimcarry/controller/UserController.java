package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("join")
    public String join() {
        return "/joinLogin/joinForm";
    }

    @PostMapping("checkIdentificationDuplicate")
    @ResponseBody
    public boolean checkIdentificationDuplicate(String userIdentification) {
        return userService.checkIdentificationDuplicate(userIdentification);
    }

    @PostMapping("checkEmailDuplicate")
    @ResponseBody
    public boolean checkEmailDuplicate(String userEmail) {
        return userService.checkEmailDuplicate(userEmail);
    }

    @PostMapping("join")
    public RedirectView join(UserVO userVO) {
        userService.registerUser(userVO);
        return new RedirectView("/user/login");
    }

    @GetMapping("sendSMS")
    @ResponseBody
    public String sendSMS(String userPhone) throws CoolsmsException {
        return userService.sendRandomNumber(userPhone);
    }

    @GetMapping("login")
    public String login() {
        return "/joinLogin/login";
    }

    @PostMapping("login")
    public RedirectView login(String userIdentification, String userPassword, HttpSession session) {
        Long userId = userService.login(userIdentification, userPassword);

        if(userId == null) {
            session.setAttribute("userId", userId);
            return new RedirectView("/user/login?login=fail");
        }

        return new RedirectView("/main/");
    }

    @GetMapping("find-id-phone")
    public String findIdPhone() {
        return "/joinLogin/find-id-phone";
    }

    @PostMapping("find-id-phone")
    public String findIdPhone(String userName, String userPhone) {
        return "";
    }

    @GetMapping("find-id-result")
    public String findIdResult() {
        return "/joinLogin/find-id-result";
    }

    @GetMapping("find-id-email")
    public String findIdEmail() {
        return "/joinLogin/find-id-email";
    }

    @GetMapping("find-password-phone")
    public String findPasswordPhone() {
        return "/joinLogin/find-password-phone";
    }

    @GetMapping("find-password-email")
    public String findPasswordEmail() {
        return "/joinLogin/find-password-email";
    }
}
