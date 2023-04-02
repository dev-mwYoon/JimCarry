package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.FileVO;
import com.app.jimcarry.service.InquiryFileService;
import com.app.jimcarry.service.StorageFileService;
import org.apache.ibatis.javassist.compiler.ast.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/file/*")
public class FileController {

    @Autowired
    private  InquiryFileService inquiryFileService;
    private StorageFileService storageFileService;
    private FileVO fileVO;

    /*메인 이미지 파일*/
    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

    // 파일 업로드
    @PostMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        return inquiryFileService.uploadFile(multipartFiles);
    }

    // 파일 저장
   /* @PostMapping("files/save/{id}")
    @ResponseBody
    public void saveFile(@RequestBody List<FileVO> files, @PathVariable Long id, String table) {

        if (table.equals("inquiry")) inquiryFileService.registerFile(files, id);
        else if (table.equals("storage")) storageFileService.registerFile(files, id);
    }*/

}

