package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.MailVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Controller;
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

    @GetMapping("join-select")
    public String joinSelect() {
        return "joinLogin/join-select";
    }

    @GetMapping("callback")
    public String callback() {
        return "/joinLogin/callback";
    }

    @PostMapping("identifications-duplicate")
    @ResponseBody
    public boolean checkIdentificationDuplicate(String userIdentification) {
        return userService.checkIdentificationDuplicate(userIdentification);
    }

    @PostMapping("emails-duplicate")
    @ResponseBody
    public boolean checkEmailDuplicate(String userEmail) {
        return userService.checkEmailDuplicate(userEmail);
    }

    @PostMapping("join")
    public RedirectView join(UserVO userVO) {
        userService.registerUser(userVO);
        return new RedirectView("/user/login");
    }

    @GetMapping("send-sms")
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
        UserVO userVO = userService.login(userIdentification, userPassword);

        if(userVO == null) {
            return new RedirectView("/user/login?login=fail");
        }

        session.setAttribute("userId", userVO.getUserId());
        session.setAttribute("userName", userVO.getUserName());
        return new RedirectView("/main/");
    }

    @GetMapping("find-id-phone")
    public String findIdPhone() {
        return "/joinLogin/find-id-phone";
    }

    @PostMapping("find-id-phone")
    public RedirectView findIdPhone(String userIdentification, String userName, String userPhone, RedirectAttributes redirectAttributes) {
        UserVO userVO = userService.findIdByPhone(userIdentification, userName, userPhone);

        if(userVO == null) {
            return new RedirectView("/user/find-id-phone?result=fail");
        }
        redirectAttributes.addFlashAttribute("userIdentification", userVO.getUserIdentification());

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

    @PostMapping("find-id-email")
    public RedirectView findIdEmail(String userIdentification, String userName, String userEmail, RedirectAttributes redirectAttributes) {
        UserVO userVO = userService.findIdByEmail(userIdentification, userName, userEmail);

        if(userVO == null) {
            return new RedirectView("/user/find-id-email?result=fail");
        }
        redirectAttributes.addFlashAttribute("userIdentification", userVO.getUserIdentification());
        return new RedirectView("/user/find-id-result");
    }

    @GetMapping("find-password-phone")
    public String findPasswordPhone() {
        return "/joinLogin/find-password-phone";
    }

    @PostMapping("find-password-phone")
    public RedirectView findPasswordPhone(String userIdentification, String userName, String userPhone) {
        if(userService.findIdByPhone(userIdentification, userName, userPhone) == null) {
            return new RedirectView("/user/find-password-phone?result=fail");
        }

        String randomKey = userService.randomKey();

        //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
        //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
        userService.updateUserRandomKey(userIdentification, randomKey);

        return new RedirectView("/user/changePassword?userIdentification=" + userIdentification + "&userRandomKey=" + randomKey);
    }

    @GetMapping("find-password-email")
    public String findPasswordEmail() {
        return "/joinLogin/find-password-email";
    }

    @GetMapping("changePassword")
    public String changePassword(String userIdentification, String userRandomKey) {
        if(!userService.findByIdentification(userIdentification).getUserRandomKey().equals(userRandomKey)) {
            return "/";
        }

        userService.updateUserRandomKey(userIdentification, null);
        return "/joinLogin/changePassword";
    }

//    @GetMapping("changePassword")
//    public String changePassword() {
//        return "/joinLogin/changePassword";
//    }

    @PostMapping("changePassword")
    public RedirectView changePasswordOK(String userIdentification, String userPassword) {
        userService.updateUserPassword(userIdentification, userPassword);
        return new RedirectView("/user/login");
    }

    @GetMapping("find-password-emailsend")
    public String findPasswordEmailSend() {
        return "/joinLogin/find-password-emailsend";
    }

    @PostMapping("find-password-email")
    public RedirectView findPasswordEmail(String userIdentification, String userName, String userEmail, RedirectAttributes redirectAttributes) {
        if(userService.findIdByEmail(userIdentification, userName, userEmail) == null) {
            return new RedirectView("/user/find-password-email?result=fail");
        }

        String randomKey = userService.randomKey();

        //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
        //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
        userService.updateUserRandomKey(userIdentification, randomKey);

        MailVO mailTO = new MailVO();
        mailTO.setAddress(userEmail);
        mailTO.setTitle("새 비밀번호 설정 링크입니다.");
        mailTO.setMessage("링크: http://localhost:10000/user/changePassword-email?userIdentification=" + userIdentification + "&userRandomKey=" + randomKey);
        userService.sendMail(mailTO);

        redirectAttributes.addFlashAttribute("userEmail", userEmail);
        return new RedirectView("/user/find-password-emailsend");
    }

    @GetMapping("logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/main/");
    }
}
