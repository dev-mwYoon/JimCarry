package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public void join(UserVO userVO) {
        log.info(userVO.getUserBirth());
        log.info(userVO.getUserBirth());
        log.info(userVO.getUserGender());
        userService.registerUser(userVO);
    }

    @GetMapping("sendSMS")
    @ResponseBody
    public String sendSMS(String userPhone) throws CoolsmsException {
        return userService.sendRandomNumber(userPhone);
    }
}
