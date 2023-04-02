package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageFileService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

  /*  창고최신순*/
   /* @GetMapping("")
    public String storageMain(Model model){
        List<StorageDTO> storageDTOS = storageService.getStorageDTO();
        for(StorageDTO storageDTO: storageDTOS){
            storageDTO.setFile(storageFileService.getFile(1L));
        }
        List<StorageDTO> reviews = storageService.getStorage();
        for(StorageDTO storageDTO: reviews){
            storageDTO.setFile(storageFileService.getFile(1L));
        }
        model.addAttribute("storages", storageDTOS);
        model.addAttribute("reviews", reviewService.getTotalById(1L));
        model.addAttribute("countReviews", reviews);

        return "/main/main";

    }*/
}
