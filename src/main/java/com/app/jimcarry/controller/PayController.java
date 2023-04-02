package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
//    @GetMapping("")
//    public String pay() { return "/pay/payment";}


    @PostMapping("/payment")
    public String pay(String storageTitle, String paymentMonth, String paymentAmount, Model model) {
        UserVO userVO = new UserVO();
        userVO.setUserId(7L);
        model.addAttribute("user", paymentService.getUser(userVO.getUserId()));
        model.addAttribute("storageTitle",storageTitle);
        model.addAttribute("paymentMonth", paymentMonth);
        model.addAttribute("paymentAmount", paymentAmount);
        return "/pay/payment";
    }
//    @GetMapping("payment")
//    public String pay(HttpSession httpSession) {
//        paymentService.getUser((Long) httpSession.getAttribute("userId"));
//        return "/pay/payment";
//    }
}
