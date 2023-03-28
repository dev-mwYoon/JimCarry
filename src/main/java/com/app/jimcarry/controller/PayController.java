package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay/*")
@RequiredArgsConstructor
public class PayController {
//
//    /*결제페이지*/
//    @GetMapping("")
//    public String pay() { return "/pay/payment";}

    private final PaymentService paymentService;

    @GetMapping("payment")
    public void pay(Model model){
        UserVO userVO = new UserVO();
        userVO.setUserId(4L);
        model.addAttribute("user", paymentService.getUser(userVO.getUserId()));
        model.addAttribute("order", paymentService.getUser(userVO.getUserId()));
    }

}
