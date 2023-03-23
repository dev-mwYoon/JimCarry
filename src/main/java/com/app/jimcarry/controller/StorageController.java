package com.app.jimcarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/storage/*")
public class StorageController {

    @GetMapping("register")
    public String register() { return "/storageRegister/storageRegister"; }

}
