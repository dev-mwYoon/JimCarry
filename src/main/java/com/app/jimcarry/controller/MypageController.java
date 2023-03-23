package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Base64;

@Controller
@RequestMapping("/users/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final UserService userService;

    /* ============================== 회원정보 수정 ================================ */
    @GetMapping("update")
    public String updateUser(Model model){
        model.addAttribute(new UserVO());

        return "mypage/my-info";
    }

    @PostMapping("update/{userId}")
    @ResponseBody
    public RedirectView updateUser(UserVO userVO, @PathVariable Long userId) {
        userVO.setUserId(userId);

        userService.updateUser(userVO);

        return new RedirectView("/users/mypage/update");
    }

    @PostMapping("checkIdentification/{userId}")
    @ResponseBody
    public boolean checkIdentificationDuplicate(String userIdentification, @PathVariable Long userId) {
        if (userService.getUser(userId).getUserIdentification().equals(userIdentification)) {
            return true;
        }

        return userService.checkIdentificationDuplicate(userIdentification);
    }

    @PostMapping("checkEmail/{userId}")
    @ResponseBody
    public boolean checkEmailDuplicate(String userEmail, @PathVariable Long userId) {
        if (userService.getUser(userId).getUserEmail().equals(userEmail)) {
            return true;
        }

        return userService.checkEmailDuplicate(userEmail);
    }

    /* =========================================================================== */
    /* ================================= 회원탈퇴 ================================= */
    @PostMapping("checkPassword/{userId}")
    @ResponseBody
    public boolean checkPassword(String userPassword, @PathVariable Long userId) {
        if (userService.getUser(userId).getUserPassword().equals(encryptPassword(userPassword))) {
            return true;
        }

        return false;
    }

    @DeleteMapping("delete/{userId}")
    @ResponseBody
    public RedirectView deleteUser(@PathVariable Long userId) {
        userService.removeUser(userId);

        /* 메인페이지 주소 작성 필요 */
        return new RedirectView("/main");
    }

    private String encryptPassword(String arg) {
        return new String(Base64.getEncoder().encode(arg.getBytes()));
    }

    /* =========================================================================== */
}
