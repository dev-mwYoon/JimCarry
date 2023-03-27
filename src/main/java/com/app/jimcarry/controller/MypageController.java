package com.app.jimcarry.controller;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.*;
import com.app.jimcarry.service.InquiryFileService;
import com.app.jimcarry.service.InquiryService;
import com.app.jimcarry.service.StorageService;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final UserService userService;
    private final StorageService storageService;
    private final InquiryService inquiryService;
    private final InquiryFileService inquiryFileService;

    /* ============================== 내 창고 ================================ */
    @GetMapping("mybox")
    public String myBox(Criteria criteria, Model model) {

//        page, amount
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;
        PageDTO pageDTO = null;

        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(2L);

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = storageService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("storages", storageService.getListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);

        return "mypage/myBox";
    }

    /* ============================== 이용중인 창고 ================================ */
    @GetMapping("using")
    public String using() {
        return "mypage/use-myBox";
    }

    /* ============================== 문의 사항 ================================ */
    @GetMapping("qna")
    public String goQna(Criteria criteria, Model model) {
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(2L);

        PageDTO pageDTO = null;

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = inquiryService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("inquiries", inquiryService.getListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);

        return "mypage/my-qna";
    }

    @PostMapping("qna/update")
    public RedirectView updateQna(InquiryVO inquiryVO, String page) {
        /* 추후 세션으로 변경 */
        inquiryVO.setUserId(2L);
        inquiryService. updateInquiry(inquiryVO);
        return new RedirectView("/users/mypage/qna?page=" + page);
    }

    @PostMapping("qna/delete")
    public RedirectView deleteQna(Long inquiryId, String page){
        inquiryService.removeInquiry(inquiryId);
        return new RedirectView("/users/mypage/qna?page=" + page);
    }

    /* ============================== 파일 ================================ */

    @PostMapping("files/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        return inquiryFileService.uploadFile(multipartFiles);
    }

    @GetMapping("files/display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

    @GetMapping("files/thumbnail/{id}")
    @ResponseBody
    public List<InquiryFileVO> display(@PathVariable Long id){
        return inquiryFileService.getList(id);
    }

    @PostMapping("files/save")
    @ResponseBody
    public void saveFile(@RequestBody List<InquiryFileVO> files, String page, String prev) {

        files.forEach(file -> log.info("inquiryId.......... : " + file.getInquiryId()));
        inquiryFileService.registerFile(files);
    }

    /* ================================ 내 후기 ================================== */
    @GetMapping("review")
    public String review() {
        return "mypage/my-review";
    }

    /* ============================== 회원정보 수정 ================================ */
    @GetMapping("update")
    public String updateUser(Model model) {
        /* 나중에 세션으로 수정 */
        UserVO userVO = userService.getUser(2L);
        model.addAttribute(userVO);
        String[] births = userVO.getUserBirth().split("-");
        model.addAttribute("birthFirtst", births[0]);
        model.addAttribute("birthMiddle", births[1]);
        model.addAttribute("birthLast", births[2]);

        return "mypage/my-info";
    }

    @PostMapping("update")
    @ResponseBody
    public RedirectView updateUser(UserVO userVO) {
        /* 나중에 세션으로 수정 */
        UserVO temp = userService.getUser(2L);
        userVO.setUserId(2L);
        userVO.setUserAddress(temp.getUserAddress());
        userVO.setUserAddressDetail(temp.getUserAddressDetail());
        userVO.setUserGender(userVO.getUserGender().equals("") ? null : userVO.getUserGender());
        userService.updateUser(userVO);

        return new RedirectView("/users/mypage/update");
    }

    @PostMapping("checkIdentification")
    @ResponseBody
    public boolean checkIdentificationDuplicate(@RequestBody Map<String, String> map) {
        String userIdentification = map.get("userIdentification");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(2L).getUserIdentification().equals(userIdentification)) {
            return true;
        }

        return userService.checkIdentificationDuplicate(userIdentification);
    }

    @PostMapping("checkEmail")
    @ResponseBody
    @LogStatus
    public boolean checkEmailDuplicate(@RequestBody Map<String, String> map) {
        String userEmail = map.get("userEmail");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(2L).getUserEmail().equals(userEmail)) {
            return true;
        }

        return userService.checkEmailDuplicate(userEmail);
    }

    /* ================================= 회원탈퇴 ================================= */
    @GetMapping("unregister")
    public String unregister() {
        return "mypage/my-withdrawal";
    }

    @PostMapping("checkPassword")
    @ResponseBody
    public boolean checkPassword(@RequestBody Map<String, String> map) {
        String userPassword = map.get("userPassword");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(2L).getUserPassword().equals(encryptPassword(userPassword))) {
            return true;
        }


        return false;
    }

    @DeleteMapping("delete")
    @ResponseBody
    public RedirectView deleteUser() {
        /* 나중에 세션으로 수정 */
        userService.removeUser(2L);

        /* 메인페이지 주소 작성 필요 */
        return new RedirectView("/main");
    }

    private String encryptPassword(String arg) {
        return new String(Base64.getEncoder().encode(arg.getBytes()));
    }

    /**
     * 검색조건 설정 메소드
     *
     * @param types    검색조건 List
     * @param criteria 페이징 정보를 담고 있는 객체, 화면에서 받아온다.
     */
    private Map<String, Object> getSearchMap(List<String> types, Criteria criteria) {
        Map<String, Object> map = new HashMap<>();
        map.put("types", new ArrayList<>(Arrays.asList("userId")));
        map.put("userId", 1L);
        map.put("startRow", criteria.getStartRow());
        map.put("amount", criteria.getAmount());

        return map;
    }
    /* =========================================================================== */
}
