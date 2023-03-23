package com.app.jimcarry.controller;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping("/users/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final UserService userService;

    /* ============================== 내 창고 ================================ */
    @GetMapping("mybox")
    public String myBox(Criteria criteria, Model model){
        // 페이지 번호가 없을 때, 디폴트 1페이지
        if(criteria.getPage() == 0){
            criteria.create(1, 10);
        }
        int totalMemberCount = userService.getList(criteria).size();
        model.addAttribute("members", userService.getList(criteria));
        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, totalMemberCount));
        model.addAttribute("memberTotal", totalMemberCount);

        return "mypage/myBox";
    }

    /* ============================== 회원정보 수정 ================================ */
    @GetMapping("update")
    public String updateUser(Model model){
        /* 나중에 세션으로 수정 */
        UserVO userVO = userService.getUser(2L);
        model.addAttribute(userVO);
        String[] births = userVO.getUserBirth().split("/");
        model.addAttribute("birthFirtst", births[0]);
        model.addAttribute("birthMiddle", births[1]);
        model.addAttribute("birthLast", births[2]);

        return "mypage/my-info";
    }

    @PostMapping("update")
    @ResponseBody
    public RedirectView updateUser(UserVO userVO) {
        /* 나중에 세션으로 수정 */
        UserVO temp = userService.getUser(2L);
        userVO.setUserId(2L);
        userVO.setUserAddress(temp.getUserAddress());
        userVO.setUserAddressDetail(temp.getUserAddressDetail());
        userVO.setUserGender(userVO.getUserGender().equals("") ? null : userVO.getUserGender());
        userService.updateUser(userVO);

        return new RedirectView("/users/mypage/update");
    }

    @PostMapping("checkIdentification")
    @ResponseBody
    public boolean checkIdentificationDuplicate(@RequestBody Map<String, String> map) {
        String userIdentification = map.get("userIdentification");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(2L).getUserIdentification().equals(userIdentification)) {
            return true;
        }

        return userService.checkIdentificationDuplicate(userIdentification);
    }

    @PostMapping("checkEmail")
    @ResponseBody
    @LogStatus
    public boolean checkEmailDuplicate(@RequestBody Map<String, String> map) {
        String userEmail = map.get("userEmail");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(2L).getUserEmail().equals(userEmail)) {
            return true;
        }

        return userService.checkEmailDuplicate(userEmail);
    }

    /* =========================================================================== */
    /* ================================= 회원탈퇴 ================================= */
    @GetMapping("unregister")
    public String unregister(){
        return "mypage/my-withdrawal";
    }

    @PostMapping("checkPassword")
    @ResponseBody
    public boolean checkPassword(@RequestBody Map<String, String> map) {
        String userPassword = map.get("userPassword");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(2L).getUserPassword().equals(encryptPassword(userPassword))) {
            return true;
        }


        return false;
    }

    @DeleteMapping("delete")
    @ResponseBody
    public RedirectView deleteUser() {
        /* 나중에 세션으로 수정 */
        userService.removeUser(2L);

        /* 메인페이지 주소 작성 필요 */
        return new RedirectView("/main");
    }

    private String encryptPassword(String arg) {
        return new String(Base64.getEncoder().encode(arg.getBytes()));
    }

    /* =========================================================================== */
}
