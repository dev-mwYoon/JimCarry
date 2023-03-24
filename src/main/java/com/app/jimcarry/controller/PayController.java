package com.app.jimcarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay/*")
public class PayController {

    /*결제페이지*/
    @GetMapping("pay")
    public String pay() { return "/pay/payment";}
}
