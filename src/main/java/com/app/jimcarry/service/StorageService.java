package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.ReviewDAO;
import com.app.jimcarry.domain.dao.StorageDAO;
import com.app.jimcarry.domain.dao.StorageFileDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {
    private final StorageDAO storageDAO;
    private final StorageFileDAO storageFileDAO;
    private final ReviewDAO reviewDAO;

    //    추가
    /*파일 저장*/
    @Transactional(rollbackFor = Exception.class)
    @LogStatus
    public void registerStorage(StorageDTO storageDTO) {
        StorageVO newStorage = storageDTO.createVO();
        storageDAO.save(newStorage);
        storageDTO.getFiles().stream().map(file -> new StorageFileVO().create(file, newStorage.getStorageId()))
                .forEach(file -> {file.setFilePath(getPath()); storageFileDAO.save(file);});
    }


    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    //    조회
    @LogStatus
    public StorageVO getStorage(Long storageId) {
        return storageDAO.findById(storageId);
    }
    public StorageDTO getStorageById(Long storageId) {

        return storageDAO.findStorageById(storageId);
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
    public void setStorage(StorageVO storageVO){ storageDAO.updateBy(storageVO);}
    public void modify(StorageVO storageVO){ storageDAO.setAdminStorageVO(storageVO);}

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

    // DTO 창고 조건 조회
    /* PageDTO를 매개변수로 받아 저장소DTO 리스트를 반환하는 메서드*/
    /* PageDTO 객체는 페이징 처리에 필요한 정보를 담고 있다.*/
    public List<StorageDTO> getStorageDTOBy(PageDTO pageDTO){
        /*StorageDAO에서 PageDTO에 맞는 저장소 정보를 가져와서 결과를 저장소DTO 리스트에 할당*/
        List<StorageDTO> storageList = storageDAO.findStorageDTOListBy(pageDTO);

        /* 저장소DTO 리스트의 각 요소마다 해당 저장소에 대한 리뷰 수를 가져와서 설정*/
        storageList.forEach(storage -> storage.setReviewCount(reviewDAO.findByStorageId(storage.getStorageId()).size()));

        /*저장소DTO 리스트를 반환*/
        return storageList;
    }

    // DTO 메인 신규창고 조회
    public List<StorageDTO> getStorageDTO(){
        /*StorageDAO에서 저장소 정보를 가져오는 메서드를 호출하여 결과를 저장소DTO 리스트에 할당*/
        List<StorageDTO> storageLists = storageDAO.findStorageDTOList();

        /*저장소DTO 리스트의 각 요소마다 해당 저장소에 대한 리뷰 수를 가져와서 설정*/
        storageLists.forEach(storage -> storage.setReviewCount(reviewDAO.findByStorageId(storage.getStorageId()).size()));

        /*저장소DTO 리스트를 반환*/
        return storageLists;
    }

    // DTO 메인 신규창고 조회
    public List<StorageDTO> getStorage(){
        /*StorageDAO에서 저장소 정보를 가져오는 메서드를 호출하여 결과를 저장소DTO 리스트에 할당*/
        List<StorageDTO> reviews = storageDAO.findStorageDTOLists();

        /*저장소DTO 리스트의 각 요소마다 해당 저장소에 대한 리뷰 수를 가져와서 설정*/
        reviews.forEach(storage -> storage.setReviewCount(reviewDAO.findByStorageId(storage.getStorageId()).size()));

        /*저장소DTO 리스트를 반환*/
        return reviews;
    }
}
