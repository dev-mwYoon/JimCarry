package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.ReviewFileVO;
import com.app.jimcarry.domain.vo.StorageFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageFileMapper {

    //    등록
    public void insert(StorageFileVO storageFileVO);

    /* 창고 아이디로 조회 */
    public List<StorageFileVO> selectByStorageId(Long storageId);

}
