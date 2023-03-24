package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.mapper.StorageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StorageDAO {

    private final StorageMapper storageMapper;

    //    추가
    public void save(StorageVO storageVO){
        storageMapper.insert(storageVO);
    };

    //    조회
    public StorageVO findById(Long storageId){
        return storageMapper.select(storageId);
    }

    //    조회
    public List<StorageVO> findAll(Criteria criteria){
        return storageMapper.selectAll(criteria);
    }

    //    회원번호로 조회
    public List<StorageVO> selectByUserId(Map<String, String> map){
        return storageMapper.selectByUserId(map);
    }

    //    목록


    //    수정

    //    삭제
}
