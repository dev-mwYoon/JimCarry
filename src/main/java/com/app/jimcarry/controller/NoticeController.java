package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.InquiryVO;
import com.app.jimcarry.service.InquiryService;
import com.app.jimcarry.service.NoticeService;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notice/*")
public class NoticeController {
    private final InquiryService inquiryService;
    private final UserService userService;
    private final NoticeService noticeService;

    @GetMapping("faq")
    public String faq() { return "/notice/faq";}

    @GetMapping("detail")
    public String detail() { return "/notice/notice-detail";}

//    @GetMapping("list")
//    public String list() { return "/notice/notice-list";}


    @GetMapping("list")
    public String list(Criteria criteria, Model model){
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        PageDTO pageDTO = null;

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

//        total = inquiryService.getTotalBy(searchDTO);
        total = noticeService.getTotal();
        pageDTO = new PageDTO().createPageDTO(criteria, total, new SearchDTO());
        model.addAttribute("total", total);
        model.addAttribute("notices", noticeService.getList(pageDTO));
        model.addAttribute("pagination", pageDTO);

        //공지사항 전체 갯수
        model.addAttribute("totalNotice", noticeService.getTotal());

      /*  log.info();*/

        return "/notice/notice-list";

    }

    @GetMapping("write")
    public String write() {

        return "/notice/qna-write";}

    @PostMapping("write")
    public RedirectView writeRegister(InquiryVO inquiryVO, HttpSession httpSession){
        log.info("1234");
        log.info(String.valueOf(httpSession.getAttribute("userId" )));
        inquiryVO.setUserId((Long)httpSession.getAttribute("userId" ));

        inquiryService.register(inquiryVO);
        /*userService.login;*/

        /*문의 등록후 이동페이지*/
        return new RedirectView ("/notice/list");

    }

    @GetMapping("info")
    public String info() { return "/notice/service-use-info";}
}
