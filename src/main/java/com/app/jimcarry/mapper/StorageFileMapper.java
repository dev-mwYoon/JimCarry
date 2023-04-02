package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.ReviewFileVO;
import com.app.jimcarry.domain.vo.StorageFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageFileMapper {

    //    등록
    public void insert(StorageFileVO storageFileVO);
}
