package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StorageVO {
    private Long storageId;
    private Long userId;
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
}
