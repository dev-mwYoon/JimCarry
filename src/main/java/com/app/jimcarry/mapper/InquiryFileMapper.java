package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.InquiryFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryFileMapper {

    //    등록
    public void insert(InquiryFileVO inquiryFileVO);

    //    파일 전체 조회
    public List<InquiryFileVO> selectAll(Long inquiryId);

    //    삭제
    public void delete(Long inquiryId);
}
