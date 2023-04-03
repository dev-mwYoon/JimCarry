package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.PaymentDAO;
import com.app.jimcarry.domain.dao.StorageDAO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentDAO paymentDAO;
    private final StorageDAO storageDAO;

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
    public PaymentDTO getPaymentId(Long payId) {
        return paymentDAO.findByPaymentId(payId);
    }

//    @LogStatus
//    public void findInfo(Long payId){
//        return paymentDAO.findInfo();
//    }

    //    전체조회
    @LogStatus
    public List<PaymentDTO> getList(PageDTO pageDTO) {
        return paymentDAO.findAll(pageDTO);
    }


    //    수정
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
    public List<PaymentDTO> getListBy(PageDTO pageDTO){
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        List<PaymentVO> payments = paymentDAO.findAllBy(pageDTO);
        List<StorageVO> storages = new ArrayList<>();

        storages = payments.stream().map(pay -> storageDAO.findById(pay.getStorageId())).collect(Collectors.toList());
        paymentDTOs = payments.stream().map(payment -> new PaymentDTO().createDTO(payment)).collect(Collectors.toList());

        for (int i = 0; i < paymentDTOs.size(); i++) {
            setPaymentDTO(paymentDTOs.get(i), storages.get(i));
        }

        return paymentDTOs;
    }

    // 조건조회 개수
    public int getTotalBy(SearchDTO searchDTO) {
        return paymentDAO.findTotalBy(searchDTO);
    }
    public int getTotalPayCount(SearchDTO searchDTO) {
        return paymentDAO.findTotalPayCount(searchDTO);
    }

    // 총 개수
    public int getTotal(){return paymentDAO.findTotal();}

    // 유저 조회
    public UserVO getUser(Long userId){
        return paymentDAO.findUserInfo(userId);
    }

    // 창고 조회
    public PaymentDTO getStorage(PaymentDTO paymentDTO){return (PaymentDTO) paymentDAO.findStorageInfo(paymentDTO);}

    private void setPaymentDTO(PaymentDTO paymentDTO, StorageVO storageVO) {
        paymentDTO.setStoragePrice(storageVO.getStoragePrice());
        paymentDTO.setStorageTitle(storageVO.getStorageTitle());
        paymentDTO.setStorageAddress(storageVO.getStorageAddress());
        paymentDTO.setStorageAddressDetail(storageVO.getStorageAddressDetail());
        paymentDTO.setStorageSize(storageVO.getStorageSize());
        paymentDTO.setStorageUseDate(storageVO.getStorageUseDate());
        paymentDTO.setStorageEndDate(storageVO.getStorageEndDate());
    }

    // 총 결제 금액 조회
    public int getTotalPay(){
        return paymentDAO.findTotalPay();
    }

}
