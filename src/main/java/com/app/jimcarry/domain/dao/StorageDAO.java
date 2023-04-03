package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.mapper.StorageMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StorageDAO {

    private final StorageMapper storageMapper;
    private final StorageDTO storageDTO;

    //    추가
   /* public void save(StorageVO storageVO) {
        storageMapper.insert(storageVO);
    }*/

    public void save(StorageDTO storageDTO) {
        storageMapper.insert(storageDTO);
    }

    //    조회
    public StorageVO findById(Long storageId) {
        return storageMapper.select(storageId);
    }

    //    조회
    public List<StorageVO> findAll(PageDTO pageDTO) {
        return storageMapper.selectAll(pageDTO);
    }
    public List<StorageDTO> findStorageAll(PageDTO pageDTO) {return storageMapper.selectStorageAll(pageDTO);}

    //    검색조건 조회
    public List<StorageVO> findBy(PageDTO pageDTO) {
        return storageMapper.selectBy(pageDTO);
    }

    //    전체개수 조회
    public int findTotal() {
        return storageMapper.total();
    }

    //    검색에 맞는 전체개수 조회
    public int findTotalBy(SearchDTO searchDTO){
        return storageMapper.totalBy(searchDTO);
    }
    //    수정

    //    삭제
    public void deleteById(Long storageId) {
        storageMapper.delete(storageId);
    }


    //    storagesDTO 조회
    public List<StorageDTO> findStorageDTOBy(Long storageId) {
        return storageMapper.selectAllBy(storageId);
    }

    //    storagesDTO 조건조회
    public List<StorageDTO> findStorageDTOListBy(PageDTO pageDTO) {
        return storageMapper.selectDTOAllBy(pageDTO);
    }

    //    storagesDTO 메인 신규창고조회
    public List<StorageDTO> findStorageDTOList() {
        return storageMapper.selectDTOAll();
    }

    //    storagesDTO 메인 리뷰창고조회
    public List<StorageDTO> findStorageDTOLists() {return storageMapper.selectStorageDTOAll();
    }
}
