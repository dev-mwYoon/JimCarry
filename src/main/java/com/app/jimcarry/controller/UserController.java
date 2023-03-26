package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
            return new RedirectView("/user/login?login=fail");
        }

        session.setAttribute("userId", userId);
        return new RedirectView("/main/");
    }

    @GetMapping("find-id-phone")
    public String findIdPhone() {
        return "/joinLogin/find-id-phone";
    }

    @PostMapping("find-id-phone")
    public RedirectView findIdPhone(String userName, String userPhone, RedirectAttributes redirectAttributes) {
        String userIdentification = userService.findIdByPhone(userName, userPhone);
        log.info(userIdentification);

        if(userIdentification == null) {
            return new RedirectView("/user/find-id-phone?result=fail");
        }
        redirectAttributes.addFlashAttribute("userIdentification", userIdentification);

        return new RedirectView("/user/find-id-result");
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

    @PostMapping("find-password-phone")
    public RedirectView findPasswordPhone(String userIdentification, RedirectAttributes redirectAttributes) {

        if(userService.checkIdentificationDuplicate(userIdentification)) {
            return new RedirectView("/user/find-password-phone?result=fail");
        }
        redirectAttributes.addFlashAttribute("userIdentification", userIdentification);

        return new RedirectView("/user/changePassword");
    }

    @GetMapping("find-password-email")
    public String findPasswordEmail() {
        return "/joinLogin/find-password-email";
    }

    @GetMapping("changePassword")
    public String changePassword() {
        return "/joinLogin/changePassword";
    }

    @GetMapping("find-password-emailsend")
    public String findPasswordEmailSend() {
        return "/joinLogin/find-password-emailsend";
    }

    @GetMapping("logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/main/");
    }
}
