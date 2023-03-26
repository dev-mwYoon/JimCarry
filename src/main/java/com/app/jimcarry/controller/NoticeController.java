package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.InquiryVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.InquiryService;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notice/*")
public class NoticeController {
    private  final InquiryService inquiryService;
    private  final UserService userService;

    @GetMapping("faq")
    public String faq() { return "/notice/faq";}

    @GetMapping("detail")
    public String detail() { return "/notice/notice-detail";}

    @GetMapping("list")
    public String list() { return "/notice/notice-list";}

    @GetMapping("write")
    public String write() {

        return "/notice/qna-write";}

    @PostMapping("write")
    public RedirectView writeRegister(InquiryVO inquiryVO, HttpSession httpSession){
        log.info("1234");
        log.info(String.valueOf(httpSession.getAttribute("userId" )));
        inquiryVO.setUserId((Long)httpSession.getAttribute("userId" ));



        inquiryService.register(inquiryVO);
        /*userService.login;*/


        return new RedirectView ("notice/list");

    }

    @GetMapping("info")
    public String info() { return "/notice/service-use-info";}
}
