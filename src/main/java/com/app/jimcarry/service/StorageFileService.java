package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.FileDAO;
import com.app.jimcarry.domain.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageFileService implements FileService {
    private final FileDAO fileDAO;

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
}
