package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryVO {
    private Long inquiryId;
    private Long userId;
    private String inquiryTitle;
    private String inquiryContent;
    private String inquiryRegist;
    private Integer inquiryAnswer;

}
