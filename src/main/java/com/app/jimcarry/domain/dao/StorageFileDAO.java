package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.StorageFileVO;
import com.app.jimcarry.mapper.StorageFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StorageFileDAO {
    private final StorageFileMapper storageFileMapper;

    public void save(StorageFileVO storageFileVO) {
        storageFileMapper.insert(storageFileVO);
    }

}
