package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PaymentDTO;
import com.app.jimcarry.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {

//    //    결제조회
//    public PaymentDTO findById(Long payId){
//        return PaymentMapper.select(payId);
//    };
//
//    //    회원조회
//    public PaymentDTO select(Long userId){
//        return PaymentMapper.selectByUserId(userId);
//    };
//
//    //    창고조회
//    public PaymentDTO select(Long storageId){
//        return PaymentMapper.selectByStorageId(storageId);
//    };
//
//    //    결제 날짜 조회
//    public PaymentDTO select(String paymentDate){
//        return PaymentDTO.selectByPaymentDate(paymentDate);
//    };
}
