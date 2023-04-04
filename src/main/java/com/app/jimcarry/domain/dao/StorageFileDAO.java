package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.StorageFileVO;
import com.app.jimcarry.mapper.StorageFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StorageFileDAO {
    private final StorageFileMapper storageFileMapper;

    public void save(StorageFileVO storageFileVO) {
        storageFileMapper.insert(storageFileVO);
    }

    /* 창고 아이디로 파일 목록 조회*/
    public List<StorageFileVO> findByStorageId(Long storageId) {
        return storageFileMapper.selectByStorageId(storageId);
    }
}
