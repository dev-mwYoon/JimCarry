package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageFileService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
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
    @GetMapping("")
    public String storageMain(Model model){
        List<StorageDTO> storageDTOS = storageService.getStorageDTO();
        for(StorageDTO storageDTO: storageDTOS){
            storageDTO.setFiles(storageFileService.getByStorageId(storageDTO.getStorageId()));
        }
        List<StorageDTO> reviewDTOs = storageService.getStorage();
        for(StorageDTO storageDTO: reviewDTOs){
            storageDTO.setFiles(storageFileService.getByStorageId(1L));
        }
        model.addAttribute("storages", storageDTOS);
        model.addAttribute("countReviews", reviewDTOs);
        model.addAttribute("reviews", reviewService.getTotalById(1L));

        return "/main/main";

    }

    /*창고 이미지 불러오기*/
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }
}
