package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileVO {
    private Long fileId;
    private String filePath;
    private String fileOrgName;
    private String fileUuid;
}
