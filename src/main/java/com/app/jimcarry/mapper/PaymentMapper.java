package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.PaymentDTO;
import com.app.jimcarry.domain.vo.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

//    //    결제조회
//    public PaymentDTO select(Long payId);
//
//    //    회원조회
//    public PaymentDTO select(Long userId);
//
//    //    창고조회
//    public PaymentDTO select(Long storageId);

    //    결제 날짜 조회
    public PaymentDTO select(String paymentDate);
}
