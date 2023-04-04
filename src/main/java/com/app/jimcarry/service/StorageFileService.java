package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.FileDAO;
import com.app.jimcarry.domain.dao.StorageFileDAO;
import com.app.jimcarry.domain.vo.FileVO;
import com.app.jimcarry.domain.vo.InquiryFileVO;
import com.app.jimcarry.domain.vo.ReviewFileVO;
import com.app.jimcarry.domain.vo.StorageFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageFileService implements FileService {
    private final FileDAO fileDAO;
    private final StorageFileDAO storageFileDAO;

    @Override
    public <T> List<T> getList(Long inquiryId) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    /*조회*/
    @LogStatus
    public FileVO getFile(Long fileId) {return fileDAO.findById(fileId);}

    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

    /*파일 저장*/
   /* @Transactional(rollbackFor = Exception.class)
    @LogStatus
    public void registerStorageFile(List<FileVO> files, Long storageId) {
        *//*storageFileDAO.deleteById(reviewId);*//*
        files.stream().map(file -> new StorageFileVO().create(file, storageId))
                .forEach(file -> {
                    file.setFilePath(getPath());
                    storageFileDAO.save(file);
                });
    }*/

    /*창고등록 파일저장*/

    @LogStatus
    public void storageFile(List<FileVO> files, Long storageId) {
        files.stream().map(file -> new StorageFileVO().create(file, storageId))
                .forEach(file -> {
                    file.setFilePath(getPath());
                    storageFileDAO.save(file);
                });
    }
    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    /*창고 아이디로 조회*/
    public List<StorageFileVO> getByStorageId(Long storageId) {
        return storageFileDAO.findByStorageId(storageId);
    }


}
