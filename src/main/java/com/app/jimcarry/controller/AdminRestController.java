package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dao.PaymentDAO;
import com.app.jimcarry.domain.dto.*;
import com.app.jimcarry.domain.vo.*;
import com.app.jimcarry.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins/*")
@RequiredArgsConstructor
@Slf4j
public class AdminRestController {
    private final UserService userService;
    private final PaymentService paymentService;
    private final InquiryService inquiryService;
    private final InquiryFileService inquiryFileService;
    private final NoticeService noticeService;
    private final StorageService storageService;
    private final StorageFileService storageFileService;
    private final ReviewService reviewService;
    private final ReviewFileService reviewFileServicece;




    /* 회원관리 상세보기 */
    @PostMapping("user/detail")
    public UserVO getUser(Long userId){
        UserVO userdetail = Optional.ofNullable(userService.getUser(userId)).get();
        return userdetail;
    }
    /* 회원 삭제 */
    @PostMapping("user/delete")
    public boolean removeUser(String[] userIds){
        Arrays.asList(userIds).stream().forEach(data -> userService.removeUser(Long.valueOf(data)));
        return true;
    }

    /* 문의관리 상세보기 */
    @PostMapping("inquiry/detail")
    public InquiryDTO getInquiry(Long inquiryId){

        if (inquiryId == null) {
            // 예외 처리
            throw new IllegalArgumentException("inquiryId must not be null");
        }
        InquiryDTO inquirydetail = Optional.ofNullable(inquiryService.getDTOInquiryId(inquiryId)).get();
        inquirydetail.setFiles(inquiryFileService.getList(inquiryId));
        return inquirydetail;
    }
    /* 문의사항 삭제 */
    @PostMapping("enquiry/delete")
    @ResponseBody
    public boolean removeinquiry(String[] inquiryIds){
        Arrays.asList(inquiryIds).stream().forEach(data -> inquiryService.removeInquiry(Long.valueOf(data)));
        return true;
    }
    /* 문의하기 답변 */
    @PostMapping("inquiry/answer")
    public boolean changeAnswer(String[] answerCheck){
        for (String id : answerCheck) {
            InquiryVO inquiry = inquiryService.getInquiry(Long.valueOf(id));
            if(inquiry.getInquiryAnswer() == 1){
                inquiry.setInquiryAnswer(0);
            }else {
                inquiry.setInquiryAnswer(1);
            }
            inquiryService.updateInquiry(inquiry);

        }
        return true;
    }


    /* 결제관리 상세보기 */
    @PostMapping("payment/detail")
    public PaymentDTO getPay(Long payId){
        PaymentDTO paydetail = Optional.ofNullable(paymentService.getPaymentId(payId)).get();
        return paydetail;
    }
    /* 결제내역 삭제 */
    @PostMapping("payment/delete")
    public boolean removePayment(String[] payIds){
        Arrays.asList(payIds).stream().forEach(data -> paymentService.removePayment(Long.valueOf(data)));
        return true;
    }

    /* 공지사항 상세보기 */
    @PostMapping("notice/detail")
    public NoticeVO getNotice(Long noticeId){
        NoticeVO noticedetail = Optional.ofNullable(noticeService.getNotice(noticeId)).get();
        return noticedetail;
    }
    /*공지사항 삭제*/
    @PostMapping("notice/delete")
    public boolean removeNotice(String[] noticeIds) {
        Arrays.asList(noticeIds).stream().forEach(data -> noticeService.removeNotice(Long.valueOf(data)));
        return true;
    }

    /* 창고관리 상세보기 */
    @PostMapping("storage/detail")
    public StorageDTO getStorage(@RequestParam Long storageId){

        StorageDTO storagedetail = Optional.ofNullable(storageService.getStorageById(storageId)).get();
        storagedetail.setFiles(storageFileService.getByStorageId(storageId));
        return storagedetail;
    }
    /* 창고 삭제 */
    @PostMapping("storage/delete")
    @ResponseBody
    public boolean removeStorage(String[] storageIds){
        Arrays.asList(storageIds).stream().forEach(data -> storageService.removeStorage(Long.valueOf(data)));
        return true;
    }

    /* 리뷰관리 상세보기*/
    @PostMapping("review/detail")
    public ReviewDTO getReview(Long reviewId){
        ReviewDTO reviewdetail = Optional.ofNullable(reviewService.getReviewById(reviewId)).get();
        reviewdetail.setFileVOS(reviewFileServicece.getListByReviewId(reviewId));
        return reviewdetail;
    }
    /* 리뷰 삭제 */
    @PostMapping("review/delete")
    @ResponseBody
    public boolean removeReview(String[] reviewIds){
        Arrays.asList(reviewIds).stream().forEach(data -> reviewService.removeReview(Long.valueOf(data)));
        return true;
    }


}
