package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
    @Qualifier("mypageUserService")
    private final UserService userService;

    @PostMapping("update/{userId}")
    @ResponseBody
    public void updateUser(UserVO userVO, @PathVariable Long userId){
        userVO.setUserId(userId);
        try {
            userService.updateUser(userVO);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


}
