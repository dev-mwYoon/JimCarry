package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins/*")
@RequiredArgsConstructor
@Slf4j
public class AdminRestController {
    private final UserService userService;



    // 상세보기
//    @PostMapping("user/detail")
//    public UserVO getUser(Long userId){
//        UserVO userdetail = Optional.ofNullable(userService.getUser(userId)).get();
//        return userdetail;
//    }
}
