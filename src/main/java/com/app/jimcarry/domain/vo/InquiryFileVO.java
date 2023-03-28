package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryFileVO extends FileVO {
    private Long inquiryId;

    public InquiryFileVO create(FileVO fileVO, Long id){
        super.setFilePath(fileVO.getFilePath());
        super.setFileOrgName(fileVO.getFileOrgName());
        super.setFileUuid(fileVO.getFileUuid());
        this.inquiryId = id;

        return this;
    }
}
