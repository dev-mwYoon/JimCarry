package com.app.jimcarry.domain.dto;

import com.app.jimcarry.domain.vo.FileVO;
import com.app.jimcarry.domain.vo.ReviewVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

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

    List<FileVO> files;

    public ReviewVO createVO() {
        ReviewVO reviewVO = new ReviewVO();

        reviewVO.setReviewId(this.reviewId);
        reviewVO.setUserId(this.userId);
        reviewVO.setStorageId(this.storageId);
        reviewVO.setReviewTitle(this.reviewTitle);
        reviewVO.setReviewContext(this.reviewContext);
        reviewVO.setReviewWriteDate(this.reviewWriteDate);
        reviewVO.setReviewEditDate(this.reviewEditDate);

        return reviewVO;
    }
}
