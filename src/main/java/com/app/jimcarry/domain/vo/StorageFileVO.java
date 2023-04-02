package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StorageFileVO extends FileVO {
    private Long storageId;

    public StorageFileVO create(FileVO fileVO, Long id) {
        super.setFilePath(fileVO.getFilePath());
        super.setFileOrgName(fileVO.getFileOrgName());
        super.setFileUuid(fileVO.getFileUuid());
        this.storageId = id;

        return this;
    }
}

