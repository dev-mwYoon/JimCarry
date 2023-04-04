package com.app.jimcarry.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReviewFileDTO {
    private Long reviewId;
    private Long userId;
    private Long storageId;
    private String reviewTitle;
    private String reviewContext;
    private String reviewWriteDate;
    private String reviewEditDate;
    private Long reviewFileId;
    private String reviewFilePath;
    private String reviewFileOrgName;
    private String reviewFileUuid;

}
