package com.app.jimcarry.domain.dto;

import com.app.jimcarry.domain.vo.FileVO;
import com.app.jimcarry.domain.vo.InquiryFileVO;
import com.app.jimcarry.domain.vo.InquiryVO;
import com.app.jimcarry.domain.vo.ReviewVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class InquiryDTO {
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
    private Long inquiryId;
    private Long storageId;
    private String inquiryTitle;
    private String inquiryContent;
    private String inquiryRegist;
    private Integer inquiryAnswer;

    private List<InquiryFileVO> files;

    public InquiryVO createVO() {
        InquiryVO inquiryVO = new InquiryVO();

        inquiryVO.setInquiryId(this.inquiryId);
        inquiryVO.setUserId(this.userId);
        inquiryVO.setInquiryTitle(this.inquiryTitle);
        inquiryVO.setInquiryContent(this.inquiryContent);
        inquiryVO.setInquiryRegist(this.inquiryRegist);
        inquiryVO.setInquiryAnswer(this.inquiryAnswer);

        return inquiryVO;
    }




}
