package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainController {
    private final StorageService storageService;

//    /*메인페이지*/
//    @GetMapping("")
//    public String mainPage(){
//        return "/main/main";
//    }

    /*창고최신순*/
    @GetMapping("")
    public String storageMain(Model model){
        model.addAttribute("storages", storageService.getStorageDTO());
        return "/main/main";
    }

}