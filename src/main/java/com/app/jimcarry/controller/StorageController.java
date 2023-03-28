package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/storages/*")
@RequiredArgsConstructor
@Slf4j
public class StorageController {

    private final StorageService storageService;
    private final ReviewService reviewService;

    @GetMapping("register")
    public String register() { return "/storageRegister/storageRegister"; }


    /*창고등록*/
    @PostMapping("register")
    public RedirectView storageSave(StorageVO storageVO, HttpSession httpSession) {
        log.info("1234");
        storageVO.setUserId((Long)httpSession.getAttribute("userId"));
        log.info("5678");
        storageService.register(storageVO);

        return new RedirectView("/main/main");
    }

    /* 전체 창고 목록 검색 */
    @GetMapping("search")
    public String searchAll() {
        
        return "/main/search-page";
    }

    /* 헤더의 지역별을 눌렀을 때 지역별 창고 목록 검색 */
    @GetMapping("search-local")
    public String localSearch() {
        return "/storages/search-page";
    }
    /*public RedirectView searchBy(StorageVO storageVO,RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("storageAddress", storageVO.getStorageAddress());
        return new RedirectView("/main/search-page");
    }*/

}
