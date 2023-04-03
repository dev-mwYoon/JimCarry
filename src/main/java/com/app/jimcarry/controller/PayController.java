package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay/*")
@RequiredArgsConstructor
@Slf4j
public class PayController {
    private final PaymentService paymentService;

//    /*결제페이지*/
    @PostMapping("/payment")
    public String pay(String storageTitle, String paymentMonth, String paymentAmount, Model model, HttpSession httpSession) {
        model.addAttribute("user", paymentService.getUser(((UserVO)(httpSession.getAttribute("user"))).getUserId()));
        model.addAttribute("storageTitle",storageTitle);
        model.addAttribute("paymentMonth", paymentMonth);
        model.addAttribute("paymentAmount", paymentAmount);
        return "/pay/payment";
    }
    @GetMapping("pay-reserve")
    public String payReserve(String paymentAmount, Model model, HttpSession httpSession){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + paymentAmount);
        model.addAttribute("userVO", (UserVO)httpSession.getAttribute("user"));
        model.addAttribute("paymentAmount", paymentAmount);
        return "/pay/pay-reserve";
    }

}
