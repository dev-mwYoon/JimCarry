package com.app.jimcarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
    @GetMapping("enquiry")
    public String enquiry() {
        return "/admin/enquiry";
    }
    @GetMapping("notice")
    public String notice() {
        return "/admin/notice";
    }
    @GetMapping("payment")
    public String payment() {
        return "/admin/payment";
    }
    @GetMapping("review")
    public String review() {
        return "/admin/review";
    }
    @GetMapping("storage")
    public String storage() {
        return "/admin/storage";
    }
    @GetMapping("user")
    public String user() {
        return "/admin/user";
    }

}
