package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.StorageVO;
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

    @GetMapping("/payment")
    public String pay(Model model) {
        UserVO userVO = new UserVO();
        userVO.setUserId(4L);
        StorageVO storageVO = new StorageVO();
        storageVO.setStorageId(1L);
        model.addAttribute("user", paymentService.getUser(userVO.getUserId()));
        model.addAttribute("storage", paymentService.getStorage(storageVO.getStorageId()));
        return "/pay/payment";
    }

}
