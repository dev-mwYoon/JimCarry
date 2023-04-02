package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.FileDAO;
import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {

    //    파일 전체 조회
    <T> List<T> getList(Long inquiryId);

    //    삭제
    void remove(Long id);

    /*void registerStorage(StorageDTO storageDTO);*/
}
