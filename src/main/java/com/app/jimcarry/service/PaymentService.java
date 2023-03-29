package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.Encryption;
import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.PaymentDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.PaymentDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.PaymentVO;
import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.domain.vo.UserVO;
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
//    @LogStatus
    @Transactional(rollbackFor = Exception.class)
    public void updatePayment(PaymentVO paymentVO) {paymentDAO.setPaymentVO(paymentVO);}


    //    삭제
    @LogStatus
    @Transactional(rollbackFor = Exception.class)
    public void removePayment(Long payId) {
        Optional.ofNullable(paymentDAO.findById(payId)).orElseThrow();
        paymentDAO.deleteById(payId);
    }

    // 조건조회
    public List<PaymentVO> getListBy(PageDTO pageDTO){
        return paymentDAO.findAllBy(pageDTO);
    }

    // 조건조회 개수
    public int geTotalBy(SearchDTO searchDTO) {
        return paymentDAO.findTotalBy(searchDTO);
    }

    // 총 개수
    public int getTotal(){return paymentDAO.findTotal();}

    // 유저 조회
    public UserVO getUser(Long userId){
        return paymentDAO.findUserInfo(userId);
    }

    // 창고 조회
    public StorageVO getStorage(Long storageId){
        return paymentDAO.findStorageInfo(storageId);
    }
}
