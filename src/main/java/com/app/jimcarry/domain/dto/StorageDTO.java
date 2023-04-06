package com.app.jimcarry.domain.dto;

import com.app.jimcarry.domain.vo.FileVO;
import com.app.jimcarry.domain.vo.InquiryVO;
import com.app.jimcarry.domain.vo.StorageFileVO;
import com.app.jimcarry.domain.vo.StorageVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@Data
public class StorageDTO {
    private Long userId;
    private String userIdentification;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String userAddressDetail;
    private String userGender;
    private String userBirth;
    private String userRandomKey;
    private Integer userStatus;
    private Long storageId;
    private String storageName;
    private String storagePhone;
    private String storageTitle;
    private String storageSize;
    private Integer storagePrice;
    private String storageAddress;
    private String storageAddressDetail;
    private String storageUseDate;
    private String storageEndDate;
    private Integer storageAddressNumber;
    private BigInteger reviewId;
    private Integer reviewCount;
    private List<StorageFileVO> files;



    public StorageVO createVO() {
        StorageVO storageVO = new StorageVO();

        storageVO.setStorageId(this.storageId);
        storageVO.setUserId(this.userId);
        storageVO.setStoragePhone(this.storagePhone);
        storageVO.setStorageName(this.storageName);
        storageVO.setStorageTitle(this.storageTitle);
        storageVO.setStorageSize(this.storageSize);
        storageVO.setStoragePrice(this.storagePrice);
        storageVO.setStorageAddress(this.storageAddress);
        storageVO.setStorageAddressDetail(this.storageAddressDetail);
        storageVO.setStorageUseDate(this.storageUseDate);
        storageVO.setStorageEndDate(this.storageEndDate);
        storageVO.setStorageAddressNumber(this.storageAddressNumber);

        return storageVO;
    }

    public StorageDTO createDTO(StorageVO storageVO){

        this.storageId = storageVO.getStorageId();
        this.userId = storageVO.getUserId();
        this.storagePhone = storageVO.getStoragePhone();
        this.storageName = storageVO.getStorageName();
        this.storageTitle = storageVO.getStorageTitle();
        this.storageSize = storageVO.getStorageSize();
        this.storagePrice = storageVO.getStoragePrice();
        this.storageAddress = storageVO.getStorageAddress();
        this.storageAddressDetail = storageVO.getStorageAddressDetail();
        this.storageUseDate = storageVO.getStorageUseDate();
        this.storageEndDate = storageVO.getStorageEndDate();
        this.storageAddressNumber = storageVO.getStorageAddressNumber();

        return this;
    }
}
