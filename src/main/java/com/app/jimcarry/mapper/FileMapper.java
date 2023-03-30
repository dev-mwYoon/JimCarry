package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    /* 파일 조회*/
    public FileVO select(Long fileId);
}
