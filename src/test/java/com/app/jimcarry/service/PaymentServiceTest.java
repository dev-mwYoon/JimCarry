package com.app.jimcarry.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    void register() {
    }

    @Test
    void getPayment() {
    }

    @Test
    void getList() {
    }
}
