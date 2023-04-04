package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.PaymentVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.PaymentService;
import com.app.jimcarry.service.StorageFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay/*")
@RequiredArgsConstructor
@Slf4j
public class PayController {
    private final PaymentService paymentService;
    private final StorageFileService storageFileService;

//    /*결제페이지*/
    @PostMapping("/payment")
    public String pay(String storageTitle, String paymentMonth, String paymentAmount, Long storageId, Model model, HttpSession httpSession) {
        model.addAttribute("user", paymentService.getUser(((UserVO)(httpSession.getAttribute("user"))).getUserId()));
        model.addAttribute("storageTitle",storageTitle);
        model.addAttribute("storageId",storageId);
        model.addAttribute("paymentMonth", paymentMonth);
        model.addAttribute("paymentAmount", paymentAmount);
        model.addAttribute("file", storageFileService.getByStorageId(storageId));


        return "/pay/payment";
    }

    @GetMapping("/pay-reserve")
    public String payReserve(String paymentAmount, Model model, HttpSession httpSession){
        model.addAttribute("userVO", (UserVO)httpSession.getAttribute("user"));
        model.addAttribute("paymentAmount", paymentAmount);
        return "/pay/pay-reserve";
    }

    @PostMapping("/payRegister")
    @ResponseBody
    public String payRegister(String paymentAmount, String storageId, HttpSession httpSession) {
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setPaymentAmount(Integer.parseInt(paymentAmount));
        paymentVO.setUserId(((UserVO)httpSession.getAttribute("user")).getUserId());
        paymentVO.setStorageId(Long.valueOf(storageId));
        paymentService.register(paymentVO);
        return "/pay/pay-reserve?paymentAmount=" + paymentAmount;
    }

}
