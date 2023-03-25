package com.app.jimcarry.service;

import com.app.jimcarry.domain.dao.StorageDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageDAO storageDAO;

    //    추가
    public void register(StorageVO storageVO) {
        storageDAO.save(storageVO);
    }

    //    조회
    public StorageVO getStorage(Long storageId) {
        return storageDAO.findById(storageId);
    }

    //    전체조회
    public List<StorageVO> getList(PageDTO pageDTO) {
        return storageDAO.findAll(pageDTO);
    }

    //    검색조건 (Criteria 포함)
    public List<StorageVO> getListBy(PageDTO pageDTO) {
        return storageDAO.findBy(pageDTO);
    }

    //    전체개수 조회
    public int getTotal() {
        return storageDAO.findTotal();
    }

    //    조건에 맞는 전체개수 조회
    public int getTotalBy(SearchDTO searchDTO) {
        return storageDAO.findTotalBy(searchDTO);
    }

    //    수정

    //    삭제

//
}
