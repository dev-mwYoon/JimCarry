package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentVO {
    private Long payId;
    private Long userId;
    private Long storageId;
    private String paymentDate;
    private Integer paymentAmount;

}

