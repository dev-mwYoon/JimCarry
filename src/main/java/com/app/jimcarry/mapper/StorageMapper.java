package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.StorageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageMapper {

    //    추가
    public void insert(StorageVO storageVO);

    //    조회
    public StorageVO select(Long storageId);

    //    조건조회
    public List<StorageVO> selectByUserId(Long userId);

    //    목록


    //    수정

    //    삭제
}
