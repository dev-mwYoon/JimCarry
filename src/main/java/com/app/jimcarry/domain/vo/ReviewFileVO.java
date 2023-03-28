package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewFileVO extends FileVO{
    private Long reviewId;

    public ReviewFileVO create(FileVO fileVO, Long id){
        super.setFilePath(fileVO.getFilePath());
        super.setFileOrgName(fileVO.getFileOrgName());
        super.setFileUuid(fileVO.getFileUuid());
        this.reviewId = id;

        return this;
    }
}
