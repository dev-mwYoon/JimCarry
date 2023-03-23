package com.app.jimcarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {

    @GetMapping("faq")
    public String faq() { return "/notice/faq";}

    @GetMapping("detail")
    public String detail() { return "/notice/notice-detail";}

    @GetMapping("list")
    public String list() { return "/notice/notice-list";}

    @GetMapping("write")
    public String write() { return "/notice/qna-write";}

    @GetMapping("info")
    public String info() { return "/notice/service-use-info";}
}
