package com.app.jimcarry.domain.dto;

import com.app.jimcarry.domain.vo.FileVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class PaginationDTO {
    private List<StorageDTO> storageDTO;
    private PageDTO pageDTO;
}
