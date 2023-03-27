package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.InquiryFileDAO;
import com.app.jimcarry.domain.vo.FileVO;
import com.app.jimcarry.domain.vo.InquiryFileVO;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InquiryFileService implements FileService {

    private final InquiryFileDAO inquiryFileDAO;

    //    등록
    @Override
    @LogStatus
    @Transactional(rollbackFor = Exception.class)
    public void register(FileVO inquiryFileVO) {
        inquiryFileDAO.save((InquiryFileVO) inquiryFileVO);
    }

    //    조회
    @Override
    public List<InquiryFileVO> getList(Long inquiryId) {
        return inquiryFileDAO.findAll(inquiryId);
    }

    //    삭제
    @Override
    @LogStatus
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
    }

    //    파일 업로드
    @LogStatus
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> uploadFile(List<MultipartFile> multipartFiles) throws IOException {
        Map<String, Object> map = new HashMap<>();

        List<String> uuids = new ArrayList<>();
        List<String> filePaths = new ArrayList<>();
        String path = FileVO.ABSOLUTE_PATH + "/" + getPath();
        String filePath = "";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
//
        for (int i = 0; i < multipartFiles.size(); i++) {
            uuids.add(UUID.randomUUID().toString());
            filePath = uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename();
            /* multipartFiles로 가져온 파일을 path, uuid, fileOriginalName 을 File 객체로 만들어 저장 */
            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));

            /* 해당 파일이 이미지인 경우 썸네일도 저장 */
            if (multipartFiles.get(i).getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 150, 150);
                out.close();
                filePath = "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename();
            }

            filePaths.add(getPath() + "/" + filePath);
        }
//
        map.put("uuids", uuids);
        map.put("paths", filePaths);
        return map;
    }

    //    추가
    @Transactional(rollbackFor = Exception.class)
    @LogStatus
    public void registerFile(List<InquiryFileVO> files) {
        inquiryFileDAO.deleteById(files.get(0).getInquiryId());
        files.forEach(file -> {
            file.setFilePath(getPath());
            inquiryFileDAO.save(file);
        });
    }

    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
