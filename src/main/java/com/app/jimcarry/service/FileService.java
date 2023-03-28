package com.app.jimcarry.service;

import com.app.jimcarry.domain.vo.FileVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface FileService {

    //    파일 전체 조회
    <T> List<T> getList(Long inquiryId);

    //    삭제
    void remove(Long id);

}
