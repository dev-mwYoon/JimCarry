package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.InquiryDAO;
import com.app.jimcarry.domain.dto.InquiryDTO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryDAO inquiryDAO;

    //    추가
    /* 단위테스트 작성 필요 */
    @LogStatus
    @Transactional(rollbackFor = Exception.class)
    public void register(InquiryVO inquiryVO) {
        inquiryDAO.save(inquiryVO);
    }

    //    조회
    @LogStatus
    public InquiryVO getInquiry(Long inquiryId) {
        return inquiryDAO.findById(inquiryId);
    }

    //    전체조회
    @LogStatus
    public List<InquiryDTO> getList(PageDTO pageDTO){ return inquiryDAO.findAll(pageDTO);}

    //    전체조회 개수

    //    조건조회
    /**
     * @param pageDTO 화면에서 받아온 페이징처리 정보, Criteria, SearchDTO 포함
     * */
    @LogStatus
    public List<InquiryVO> getListBy(PageDTO pageDTO){
        return inquiryDAO.findListBy(pageDTO);
    }

    //    조건조회 개수
    /**
     * @param searchDTO Controller 에서 설정한 검색조건
     * */
    @LogStatus
    public int getTotalBy(SearchDTO searchDTO){
        return inquiryDAO.findTotalBy(searchDTO);
    }

    //    수정

//    삭제
}
