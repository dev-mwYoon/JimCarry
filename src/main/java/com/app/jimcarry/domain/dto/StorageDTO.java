package com.app.jimcarry.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

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
    private BigInteger reviewId;
}
