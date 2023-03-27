package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.InquiryDTO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.InquiryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryMapper {
    //    추가
    public void insert(InquiryVO inquiryVO);

    //    조회
    public InquiryVO select(Long inquiryId);

    //    전체조회
    public List<InquiryDTO> selectAll(PageDTO pageDTO);

    //    전체조회 개수
    //    public int totalBy();

    //    조건조회
    public List<InquiryVO> selectBy(@Param("page") PageDTO pageDTO);

    //    조건조회 개수
    public int totalBy(@Param("page") SearchDTO searchDTO);

    //    수정
    public void update(InquiryVO inquiryVO);
//    삭제
    public void delete(Long inquiryId);
}
