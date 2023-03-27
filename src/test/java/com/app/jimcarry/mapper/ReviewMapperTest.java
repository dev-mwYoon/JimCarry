package com.app.jimcarry.mapper;


import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
//@Transactional
public class ReviewMapperTest {
    @Autowired
    PageDTO pageDTO;

    @Autowired
    ReviewDTO reviewDTO;

    @Autowired
    ReviewVO reviewVO;

    @Autowired
    ReviewMapper reviewMapper;

    /* 리뷰 추가 */
    @Test
    public void insert(){
        ReviewVO reviewVO = new ReviewVO();
        reviewVO.setUserId(6L);
        reviewVO.setStorageId(8L);
        reviewVO.setReviewTitle("창고리뷰 제목 2");
        reviewVO.setReviewContext("창고리뷰 내용 2");
        reviewMapper.insert(reviewVO);
    }

    /*리뷰 전체 목록 조회*/
    @Test
    public void getList(){
            Criteria criteria = new Criteria().create(1, 10);
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setDesc(true);
            reviewMapper.selectAll(new PageDTO().createPageDTO(criteria, reviewMapper.total(), searchDTO));
    }

}
