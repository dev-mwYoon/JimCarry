package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.PaymentVO;
import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

//    //    결제조회
//    public PaymentVO select(Long payId);
//
//    //    회원조회
//    public UserVO select(Long userId);
//
//    //    창고조회
//    public StorageVO select(Long storageId);

    //    결제 날짜 조회
    public UserVO select(String paymentDate);
}
