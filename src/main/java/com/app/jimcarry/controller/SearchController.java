package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.PaginationDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageFileService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("storages/search/*")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final ReviewService reviewService;
    private final StorageService storageService;
    private final StorageFileService storageFileService;

    /*지역별 창고 목록 검색*/
    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> searchByAddress(Integer storageAddressNumber, Criteria criteria){

        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 6;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        /*창고주소번호 */
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("storageAddressNumber")));
        searchDTO.setStorageAddressNumber(storageAddressNumber);

        PaginationDTO paginationDTO = new PaginationDTO();

        //         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = storageService.getTotalBy(searchDTO);
        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        List<StorageDTO> storageList = storageService.getStorageDTOBy(pageDTO);

        paginationDTO.setPageDTO(pageDTO);
        paginationDTO.setStorageDTO(storageList);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("paginationDTO", paginationDTO);

        return resultMap;
    }

    /* 창고 상세페이지 조회*/
    @GetMapping("detail/{storageId}")
    public String searchDetail(@PathVariable("storageId") Long storageId, Model model, Criteria criteria){
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 3;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("storageId")));
        searchDTO.setStorageId(storageId);

        //         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = reviewService.getTotalBy(searchDTO);
        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("reviews", reviewService.getListBy(pageDTO));
        model.addAttribute("storages", storageService.getStorageBy(storageId).get(0));
        model.addAttribute("pagination", pageDTO);

        return "/detail-info/detail-info";
    }
    
    /* 창고 검색 */
    @PostMapping("keyword")
    public RedirectView registerProduct(String storageAddress, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("storageAddress", storageAddress);
        return new RedirectView("/storages/search/keyword");
    }


    /* 창고 검색 목록*/
    @GetMapping("keyword")
    public String searchByKeyword(String storageAddress, Criteria criteria, Model model) {
         /*한 페이지에 보여줄 게시글 개수 */
        int amount = 6;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("storageAddress")));
        searchDTO.setStorageAddress(storageAddress);

        //         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = storageService.getTotalBy(searchDTO);
        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);

//        storageService.getStorageDTOBy(pageDTO);

        model.addAttribute("total", total);
        model.addAttribute("storage", storageService.getStorageDTOBy(pageDTO));
        model.addAttribute("pagination", pageDTO);

        return "main/search-page";
    }

    /*창고 이미지 불러오기*/
    @GetMapping("files/display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }
}
