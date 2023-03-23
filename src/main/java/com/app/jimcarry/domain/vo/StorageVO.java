package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StorageVO {
    private Long storageId;
    private Long userId;
    private String storageTitle;
    private String storageSize;
    private String storagePrice;
    private String storageUseDate;
    private String storageEndDate;
}
