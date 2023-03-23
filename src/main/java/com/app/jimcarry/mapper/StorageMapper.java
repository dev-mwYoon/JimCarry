package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface StorageMapper {

    /*창고 조회*/
    public StorageVO select(Long storageId);
}
