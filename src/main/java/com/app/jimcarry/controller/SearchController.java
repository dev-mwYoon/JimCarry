package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("storages/search/*")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final ReviewService reviewService;
    private final StorageService storageService;

    /* 창고 상세 목록 */
    /*@GetMapping("detail/{storageId}")
    public String searchDetail(Model model,@PathVariable Long storageId) {
        int total = reviewService.getTotalById(storageId);

        model.addAttribute("total", total);
        model.addAttribute("reviews", reviewService.getListByStorageId(storageId));
        *//* url 파라미터로 스토리지아이디 받기*//*
        model.addAttribute("storages", storageService.getStorageDTO(storageId));

        return "/detail-info/detail-info";
    }*/

    @GetMapping("detail")
    public String searchDetail(Model model,@PathVariable Long storageId){
        return "/detail-info/detail-info";
    }
}
