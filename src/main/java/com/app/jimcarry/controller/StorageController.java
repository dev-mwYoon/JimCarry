package com.app.jimcarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
@RequestMapping("/storages/*")
@Controller
public class StorageController {

    /*상세 페이지로 이동 */
    @GetMapping("detail-info")
    public String toDetail(){
        return "/detail-info/detail-info";
    }
=======
@Controller
@RequestMapping("/storage/*")
public class StorageController {

    @GetMapping("register")
    public String register() { return "/storageRegister/storageRegister"; }


>>>>>>> ff5265c8c3b88144d9dae82a78ca59ba677b06d9
}
import org.springframework.stereotype.Controller;
