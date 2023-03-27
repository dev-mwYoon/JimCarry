package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.PaymentDTO;
import com.app.jimcarry.domain.vo.PaymentVO;
import com.app.jimcarry.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentMapper paymentMapper;

    //    추가
    public void save(PaymentVO paymentVO) {
        paymentMapper.insert(paymentVO);
    }

    //    조회
    public PaymentVO findById(Long payId) {
        return paymentMapper.select(payId);
    }

    //    전체조회
    public List<PaymentDTO> findAll(PageDTO pageDTO) {
        return paymentMapper.selectAll(pageDTO);
    }

    //    수정
    public void setPaymentVO(PaymentVO paymentVO) {paymentMapper.update(paymentVO);
    }

    //    삭제
    public void deleteById(Long payId) {
        paymentMapper.delete(payId);
    }
}
