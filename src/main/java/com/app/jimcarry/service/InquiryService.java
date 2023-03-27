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
import java.util.Optional;

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
    public List<InquiryDTO> getList(PageDTO pageDTO) {
        return inquiryDAO.findAll(pageDTO);
    }

    //    전체조회 개수
//    @LogStatus
    //    public int getTotalBy(){ return inquiryDAO.findTotalBy();}

    //    조건조회

    /**
     * @param pageDTO 화면에서 받아온 페이징처리 정보, Criteria, SearchDTO 포함
     */
    @LogStatus
    public List<InquiryVO> getListBy(PageDTO pageDTO) {
        return inquiryDAO.findListBy(pageDTO);
    }

    //    조건조회 개수

    /**
     * @param searchDTO Controller 에서 설정한 검색조건
     */
    @LogStatus
    public int getTotalBy(SearchDTO searchDTO) {
        return inquiryDAO.findTotalBy(searchDTO);
    }


    /**
     * @param inquiryVO 화면에서 받은 문의 제목과 내용을 담고있다.
     * @throws IllegalArgumentException 회원번호가 없는 경우
     */
    //    수정
    @LogStatus
    @Transactional
    public void updateInquiry(InquiryVO inquiryVO) {
        if (inquiryVO.getUserId() == null) {
            throw new IllegalArgumentException("문의VO에 회원번호가 없음.");
        }

        inquiryDAO.setInquiryVO(inquiryVO);
    }

    //    삭제
    /**
     * @param inquiryId 화면에서 받은 문의글 번호
     * @throws java.util.NoSuchElementException 전달받은 문의글이 존재하지 않을 때
     * */
    @LogStatus
    @Transactional
    public void removeInquiry(Long inquiryId) {
        Optional.ofNullable(inquiryDAO.findById(inquiryId)).get();
        inquiryDAO.deleteById(inquiryId);
    }
}
