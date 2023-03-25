package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.StorageService;
import com.app.jimcarry.service.UserService;
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
    /*-----------회원관리----------*/
    @GetMapping("user")
    public String user() {
        return "/admin/user";
    }

//    @PostMapping("user")
//    public List<UserVO> showUserList() {
//        return userService.getList();
//    }

    /*-----------문의사항----------*/
    @GetMapping("enquiry")
        public String enquiry() {
            return "/admin/enquiry";
    }

    /*-----------공지사항----------*/
    @GetMapping("notice")
    public String notice() {
        return "/admin/notice";
    }
    /*-----------결제관리----------*/
    @GetMapping("payment")
    public String payment() {
        return "/admin/payment";
    }
    /*-----------리뷰관리----------*/
    @GetMapping("review")
    public String review() {
        return "/admin/review";
    }
    /*-----------창고관리----------*/
    @GetMapping("storage")
    public String storage(Criteria criteria, Model model) {

        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 3;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(2L);

        log.info(criteria.getPage() + "............");
        log.info(criteria.toString());

        PageDTO pageDTO = null;

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = storageService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("storages", storageService.getListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);

        return "/admin/storage";
    }


}


