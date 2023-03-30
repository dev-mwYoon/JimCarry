package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.FileVO;
import com.app.jimcarry.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;


    /* 리뷰 조회 */
    public FileVO findById(Long fileId) { return fileMapper.select(fileId); }
}
