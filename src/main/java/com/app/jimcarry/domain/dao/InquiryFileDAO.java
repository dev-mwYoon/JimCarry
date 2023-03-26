package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.InquiryFileVO;
import com.app.jimcarry.mapper.InquiryFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InquiryFileDAO {
    private final InquiryFileMapper inquiryFileMapper;

    //    등록
    public void save(InquiryFileVO inquiryFileVO) {
        inquiryFileMapper.insert(inquiryFileVO);
    }

    //    파일 전체 조회
    public List<InquiryFileVO> findAll(Long inquiryId) {
        return inquiryFileMapper.selectAll(inquiryId);
    }

    //    삭제
    public void deleteById(Long inquiryId) {
        inquiryFileMapper.delete(inquiryId);
    }
}