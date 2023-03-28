package com.app.jimcarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/*")
public class MainController {

    /*메인페이지*/
    @GetMapping("")
    public String mainPage(){
        return "/main/main";
    }
}