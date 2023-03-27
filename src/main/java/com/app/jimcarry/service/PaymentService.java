package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.Encryption;
import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.PaymentDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.PaymentDTO;
import com.app.jimcarry.domain.vo.PaymentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentDAO paymentDAO;

    //    추가
    @LogStatus
    @Transactional(rollbackFor = Exception.class)
    public void register(PaymentVO paymentVO) {paymentDAO.save(paymentVO);
    }


    //    조회
    @LogStatus
    public PaymentVO getPayment(Long payId) {
        return paymentDAO.findById(payId);
    }


    //    전체조회
    @LogStatus
    public List<PaymentDTO> getList(PageDTO pageDTO) {
        return paymentDAO.findAll(pageDTO);
    }


    //    수정
    @Encryption
    @LogStatus
    @Transactional
    public void updatePayment(PaymentVO paymentVO) {paymentDAO.setPaymentVO(paymentVO);}


    //    삭제
    @LogStatus
    public void removePayment(Long payId) {
        Optional.ofNullable(paymentDAO.findById(payId)).orElseThrow();
        paymentDAO.deleteById(payId);
    }
}
