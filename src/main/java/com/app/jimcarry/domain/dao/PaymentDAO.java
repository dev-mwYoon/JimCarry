package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.PaymentDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.PaymentVO;
import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
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
        return paymentMapper.selectAllPayment(pageDTO);
    }

    //    수정
    public void setPaymentVO(PaymentVO paymentVO) {paymentMapper.update(paymentVO);
    }

    //    삭제
    public void deleteById(Long payId) {
        paymentMapper.delete(payId);
    }

    // 조건조회
    public List<PaymentVO> findAllBy(PageDTO pageDTO){
        return paymentMapper.selectAllBy(pageDTO);
    }

    // 조건조회 개수
    public int findTotalBy(SearchDTO searchDTO) {
        return paymentMapper.totalBy(searchDTO);
    }

    // 총 개수
    public int findTotal(){ return paymentMapper.total();}

    // 유저 조회
    public UserVO findUserInfo(Long userId){
        return paymentMapper.selectUser(userId);
    }

    // 창고 조회
    public List<PaymentDTO> findStorageInfo(PaymentDTO paymentDTO) {return paymentMapper.selectStorage(paymentDTO);}
}
