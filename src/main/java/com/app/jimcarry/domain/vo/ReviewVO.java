package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewVO {
    private Long reviewId;
    private Long userId;
    private Long storageId;
    private String reviewTitle;
    private String reviewContext;

}
