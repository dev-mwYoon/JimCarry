package com.app.jimcarry.service;

import com.app.jimcarry.domain.dao.StorageDAO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageDAO storageDAO;

    //    추가
    public void register(StorageVO storageVO){
        storageDAO.save(storageVO);
    };

    //    조회
    public StorageVO getStorage(Long storageId){
        return storageDAO.findById(storageId);
    }

    //    조회
    public List<StorageVO> getList(Criteria criteria){
        return storageDAO.findAll(criteria);
    }

    //    검색조건 (Criteria 포함)
    public List<StorageVO> getListBy(Map<String, Object> map){
        return storageDAO.findBy(map);
    }

    //    수정

    //    삭제

//
}
