package com.app.jimcarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/main/*")
public class MainController {

    /*메인페이지*/
    @GetMapping("")
    public String mainPage(){
        return "/main/main";
    }

     /*지역별 화면 이동
    @ResponseBody
    @PostMapping("search")
    public String localName(Model model) {
        model.addAttribute("localName");
        return "/detail-info/detail-info";
    }*/
}