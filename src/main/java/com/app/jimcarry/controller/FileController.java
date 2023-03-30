package com.app.jimcarry.controller;

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

    /*메인 이미지 파일*/
    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException{
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }
}
