package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.InquiryDTO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.InquiryVO;
import com.app.jimcarry.mapper.InquiryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InquiryDAO {
    private final InquiryMapper inquiryMapper;

    //    추가
    public void save(InquiryVO inquiryVO) {
        inquiryMapper.insert(inquiryVO);
    }

    //    조회
    public InquiryVO findById(Long inquiryId) {
        return inquiryMapper.select(inquiryId);
    }

    //    전체조회
    public List<InquiryDTO> findAll(PageDTO pageDTO){ return inquiryMapper.selectAll(pageDTO);}
//    전체조회 개수

    //    조건조회
    public List<InquiryVO> findListBy(PageDTO pageDTO){
        return inquiryMapper.selectBy(pageDTO);
    }

    //    조건조회 개수
    public int findTotalBy(SearchDTO searchDTO){
        return inquiryMapper.totalBy(searchDTO);
    }

    //    수정

//    삭제
}
