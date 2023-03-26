package com.app.jimcarry.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryDTO {
    private Long userId;
    private String userIdentification;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String userAddressDetail;
    private String userGender;
    private String userBirth;
    private Long inquiryId;
    private Long storageId;
    private String inquiryTitle;
    private String inquiryContent;
    private String inquiryRegist;
    private Integer inquiryAnswer;
}
