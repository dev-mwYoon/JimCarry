package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/storages/*")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping("register")
    public String register() { return "/storageRegister/storageRegister"; }

    /*@PostMapping("detail")
    public RedirectView detail(StorageVO storageVO, RedirectAttributes redirectAttributes){
        storageService.getStorage(1L);
        redirectAttributes.addAttribute("storageId", storageVO.getStorageId());
        return new RedirectView("/detail-info/detail-info");
    }*/

    @GetMapping("detail")
    public String detail() {
        return "/detail-info/detail-info";
    }

    @GetMapping("search")
    public String search() {
        return "/main/search-page";
    }


}
