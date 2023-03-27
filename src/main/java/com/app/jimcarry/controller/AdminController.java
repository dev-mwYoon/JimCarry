package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService userService;
    private final StorageService storageService;
    private final ReviewService reviewService;
    private final InquiryService inquiryService;
    private final NoticeService noticeService;

    /*-----------회원관리----------*/
    @GetMapping("user")
    public String user(Criteria criteria, Model model) {

        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);

        PageDTO pageDTO = null;

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

//        total = userService.findTotalBy(searchDTO);
        total= userService.findTotalUser();
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("users", userService.getList(pageDTO));
        model.addAttribute("pagination", pageDTO);

        // 전체 user 수
//        model.addAttribute("totalUser",userService.findTotalUser());


        return "/admin/user";
    }

//    /*-----------문의사항----------*/
    @GetMapping("enquiry")
        public String enquiry(Criteria criteria, Model model) {

        int amount = 3;
        int total = 0;

        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);

        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = inquiryService.getTotal();
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
//        model.addAttribute("getTotal", inquiryService.getTotalBy());
        model.addAttribute("inquiries", inquiryService.getList(pageDTO));
        model.addAttribute("pagination", pageDTO);
        return "/admin/enquiry";
    }
//
//    /*-----------공지사항----------*/
    @GetMapping("notice")
    public String notice(Criteria criteria, Model model) {
        int amount = 5;
        int total = 0;

        SearchDTO searchDTO = new SearchDTO();

        PageDTO pageDTO = null;

        if(criteria.getPage() == 0){
            criteria.create(1, amount);
        }else criteria.create(criteria.getPage(), amount);

        total= noticeService.getTotal();
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("notices", noticeService.getList(pageDTO));
        model.addAttribute("pagination", pageDTO);

        return "/admin/notice";
    }
    /*-----------결제관리----------*/
    @GetMapping("payment")
    public String payment() {
        return "/admin/payment";
    }
    /*-----------리뷰관리----------*/
    @GetMapping("review")
    public String review(Criteria criteria, Model model) {
        int amount = 5;
        int total = 0;

        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);

        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

//        total = reviewService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("getTotal", reviewService.getTotal());
        model.addAttribute("reviews", reviewService.getList(pageDTO));
        model.addAttribute("pagination", pageDTO);

        return "/admin/review";

    }
    /*-----------창고관리----------*/
    @GetMapping("storage")
    public String storage(Criteria criteria, Model model) {

        int amount = 5;
        int total = 0;

        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);

//        log.info(criteria.getPage() + "............");
//        log.info(criteria.toString());

        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

//        total = storageService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("getTotal", storageService.getTotal());
        model.addAttribute("storages", storageService.getStorageList(pageDTO));
        model.addAttribute("pagination", pageDTO);

        return "/admin/storage";
    }


}


