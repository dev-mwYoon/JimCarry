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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("storages/search/*")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final ReviewService reviewService;
    private final StorageService storageService;

    @GetMapping("detail")
    public String searchDetail(Criteria criteria, Model model) {

        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 3;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO();
        /*searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(2L);*/

        /*log.info(criteria.getPage() + "............");
        log.info(criteria.toString());*/

        PageDTO pageDTO = null;

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = reviewService.getTotal();
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("reviews", reviewService.getList(pageDTO));
        model.addAttribute("storages", storageService.getStorage(8L));
        model.addAttribute("pagination", pageDTO);

        return "/detail-info/detail-info";
    }
}
