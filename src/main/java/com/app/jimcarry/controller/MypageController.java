package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.MypageUserService;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users/mypage/*")
@Slf4j
public class MypageController {

    private final UserService userService;

    @Autowired
    public MypageController(@Qualifier("mypageUserService")UserService userService){
        this.userService = userService;
    }

    @PostMapping("update/{userId}")
    @ResponseBody
    public RedirectView updateUser(UserVO userVO, @PathVariable Long userId) {
        userVO.setUserId(userId);

        try {
            userService.updateUser(userVO);
        } catch (Exception e) {
            /* 에러 컨트롤러 작성완료시 수정 필요 */
            return new RedirectView("/errors/400");
        }

        return new RedirectView("/users/mypage/update");
    }


}
