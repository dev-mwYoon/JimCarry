package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dao.PaymentDAO;
import com.app.jimcarry.domain.dto.InquiryDTO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.PaymentDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.InquiryVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.InquiryService;
import com.app.jimcarry.service.PaymentService;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



    /* 회원관리 상세보기 */
    @PostMapping("user/detail")
    public UserVO getUser(Long userId){
        UserVO userdetail = Optional.ofNullable(userService.getUser(userId)).get();
        return userdetail;
    }
    /* 문의관리 상세보기 */
    @PostMapping("inquiry/detail")
    public InquiryDTO getInquiry(Long inquiryId){
//        InquiryDTO inquirydetail = Optional.ofNullable(inquiryService.getDTOInquiryId(inquiryId)).get();
//        return inquirydetail;

        if (inquiryId == null) {
            // 예외 처리
            throw new IllegalArgumentException("inquiryId must not be null");
        }
        InquiryDTO inquirydetail = inquiryService.getDTOInquiryId(inquiryId);
        return inquirydetail;
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
}
