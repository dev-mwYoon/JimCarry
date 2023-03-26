package com.app.jimcarry.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewDTO {
    private Long reviewId;
    private Long userId;
    private Long storageId;
    private String reviewTitle;
    private String reviewContext;
    private String reviewWriteDate;
    private String reviewEditDate;
    private String userIdentification;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String userAddressDetail;
    private String userGender;
    private String userBirth;
}
