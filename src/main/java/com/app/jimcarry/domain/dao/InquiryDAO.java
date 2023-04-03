package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.InquiryDTO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.InquiryFileVO;
import com.app.jimcarry.domain.vo.InquiryVO;
import com.app.jimcarry.mapper.InquiryFileMapper;
import com.app.jimcarry.mapper.InquiryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InquiryDAO {
    private final InquiryMapper inquiryMapper;
    private final InquiryFileMapper inquiryFileMapper;

    //    추가
    public void save(InquiryVO inquiryVO) {
        inquiryMapper.insert(inquiryVO);
    }

   /* public void save(InquiryFileVO inquiryFileVO) {
        inquiryFileMapper.insert(inquiryFileVO);
    }*/
    //    조회
    public InquiryVO findById(Long inquiryId) {
        return inquiryMapper.select(inquiryId);
    }

    //    전체조회
    public List<InquiryDTO> findAll(PageDTO pageDTO){ return inquiryMapper.selectAll(pageDTO);}

    //    전체 조건 조회
    public List<InquiryDTO> findAllBy(PageDTO pageDTO){ return inquiryMapper.selectAllBy(pageDTO);}

    //    전체조회 개수
    public int findTotal(){ return inquiryMapper.total();}

    //    조건조회
    public List<InquiryVO> findListBy(PageDTO pageDTO){
        return inquiryMapper.selectBy(pageDTO);
    }

    //    조건조회 개수
    public int findTotalBy(SearchDTO searchDTO){
        return inquiryMapper.totalBy(searchDTO);
    }

    //    수정
    public void setInquiryVO(InquiryVO inquiryVO){
        inquiryMapper.update(inquiryVO);
    }

    //    삭제
    public void deleteById(Long inquiryId){
        inquiryMapper.delete(inquiryId);
    }

    //    답변 완료 총 개수
    public int findAnswerTrue(){
        return inquiryMapper.totalAnswerTrue();
    }
    //    답변 대기 총 개수
    public int findAnswerFalse(){
        return inquiryMapper.totalAnswerFalse();
    }
}
