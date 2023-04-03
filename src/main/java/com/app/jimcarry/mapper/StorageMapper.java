package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StorageMapper {

    //    추가
    public void insert(StorageDTO storageDTO);

    /*public void insert(StorageVO storageVO);*/
    //    조회
    public StorageVO select(Long storageId);

    //    조건조회
    public List<StorageVO> selectBy(@Param("page") PageDTO pageDTO);

    //    목록 조회
    public List<StorageVO> selectAll(@Param("page") PageDTO pageDTO);
    public List<StorageDTO> selectStorageAll(@Param("page") PageDTO pageDTO);

    //    전체개수 조회
    public Integer total();

    //    검색에 맞는 전체개수 조회
    public Integer totalBy(@Param("page") SearchDTO searchDTO);

    //    수정

    //    삭제
    public void delete(Long storageId);

    /*storageDTO 조회*/
    public List<StorageDTO> selectAllBy(Long storageId);

    /*storageDTO 조건 조회*/
    public List<StorageDTO> selectDTOAllBy(@Param("page") PageDTO pageDTO);

    /*storageDTO 메인 신규창고 조회*/
    public List<StorageDTO> selectDTOAll();

    /*storageDTO 메인 리뷰창고 조회*/
    public List<StorageDTO> selectStorageDTOAll();
}
