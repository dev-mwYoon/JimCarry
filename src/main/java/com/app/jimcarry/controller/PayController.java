package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay/*")
@RequiredArgsConstructor
public class PayController {
    private final PaymentService paymentService;

//    /*결제페이지*/
//    @GetMapping("")
//    public String pay() { return "/pay/payment";}


    @GetMapping("/payment")
    public String pay(Model model) {
        UserVO userVO = new UserVO();
        userVO.setUserId(8L);
        model.addAttribute("user", paymentService.getUser(userVO.getUserId()));
        return "/pay/payment";
    }
//    @GetMapping("payment")
//    public String pay(HttpSession httpSession) {
//        paymentService.getUser((Long) httpSession.getAttribute("userId"));
//        return "/pay/payment";
//    }
}
