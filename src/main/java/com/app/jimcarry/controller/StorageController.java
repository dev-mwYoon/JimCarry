package com.app.jimcarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/storages/*")
@Controller
public class StorageController {

    /*상세 페이지로 이동 */
    @GetMapping("detail-info")
    public String toDetail(){
        return "/detail-info/detail-info";
    }
}
