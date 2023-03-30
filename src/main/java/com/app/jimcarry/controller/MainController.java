package com.app.jimcarry.controller;

import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageFileService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainController {
    private final StorageService storageService;
    private final ReviewService reviewService;
    private final StorageFileService storageFileService;

//    /*메인페이지*/
//    @GetMapping("")
//    public String mainPage(){
//        return "/main/main";
//    }

    /*창고최신순*/
    @GetMapping("")
    public String storageMain(Model model){
        model.addAttribute("storages", storageService.getStorageDTO());
        model.addAttribute("reviews", reviewService.getTotalById(1L));
        model.addAttribute("countReviews", storageService.getStorage());
        model.addAttribute("files",storageFileService.getFile(1L));
        return "/main/main";
    }
}