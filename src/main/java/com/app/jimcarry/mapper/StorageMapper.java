package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StorageMapper {

    //    추가
    public void insert(StorageVO storageVO);

    //    조회
    public StorageVO select(Long storageId);

    //    조건조회
    public List<StorageVO> selectByUserId(Map<String, Object> map);

    //    목록 조회
    public List<StorageVO> selectAll(Criteria criteria);

    //    수정

    //    삭제
}
