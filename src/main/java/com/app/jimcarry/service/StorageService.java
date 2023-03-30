package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.ReviewDAO;
import com.app.jimcarry.domain.dao.StorageDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageDAO storageDAO;
    private final ReviewDAO reviewDAO;

    //    추가
    @LogStatus
    @Transactional(rollbackFor = Exception.class)
    public void register(StorageVO storageVO) {
        storageDAO.save(storageVO);
    }

    //    조회
    @LogStatus
    public StorageVO getStorage(Long storageId) {
        return storageDAO.findById(storageId);
    }

    /**
     * 전체조회
     *
     * @param pageDTO 화면에서 받아온 페이징처리 정보, Criteria 포함
     * */
    @LogStatus
    public List<StorageVO> getList(PageDTO pageDTO) {
        return storageDAO.findAll(pageDTO);
    }


    /**
     * 검색조건 (Criteria 포함)
     *
     * @param pageDTO 화면에서 받아온 페이징처리 정보, Criteria, SearchDTO 포함
     * */
    @LogStatus
    public List<StorageVO> getListBy(PageDTO pageDTO) {
        return storageDAO.findBy(pageDTO);
    }

    //    전체개수 조회
    @LogStatus
    public int getTotal() {
        return storageDAO.findTotal();
    }

    /**
     * 조건에 맞는 전체개수 조회
     *
     * @param searchDTO Controller 에서 설정한 검색조건
     * */
    @LogStatus
    public int getTotalBy(SearchDTO searchDTO) {
        return storageDAO.findTotalBy(searchDTO);
    }

    //    수정

    //    삭제
    public void removeStorage(Long storageId) {
        storageDAO.deleteById(storageId);
    }


    //    DTO 창고 전체 조회
    /* Storage DTO 전체 조회*/
    @LogStatus
    public List<StorageDTO> getStorageList(PageDTO pageDTO) {
        return storageDAO.findStorageAll(pageDTO);
    }

    //    검색에 맞는 전체개수 조회
    public int getTotalDTOBy(SearchDTO searchDTO){
        return storageDAO.findTotalBy(searchDTO);
    }

    //    DTO 창고 조회
    public List<StorageDTO> getStorageBy(Long storageId){
        return storageDAO.findStorageDTOBy(storageId);
    }

    /* DTO 창고 조건 조회*/
    public List<StorageDTO> getStorageDTOBy(PageDTO pageDTO){
        List<StorageDTO> storageList = storageDAO.findStorageDTOListBy(pageDTO);
        storageList.forEach(storage ->
            storage.setReviewCount(reviewDAO.findByStorageId(storage.getStorageId()).size())
        );
        return storageList;
    }

    /* DTO 메인 신규창고 조회*/
    public List<StorageDTO> getStorageDTO(){
        return storageDAO.findStorageDTOList();
    }

    /* DTO 메인 신규창고 조회*/
    public List<StorageDTO> getStorage(){return storageDAO.findStorageDTOLists();}
}
