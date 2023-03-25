package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
/*@Transactional*/
public class ReviewDAOTest {

    @Autowired
    ReviewDAO reviewDAO;

    @Autowired
    ReviewVO reviewVO;

    @Autowired
    PageDTO pageDTO;

    /* 리뷰 목록 조회 */
    @Test
    public void findAll(PageDTO pageDTO){
        log.info(reviewDAO.findAll(pageDTO).toString());
    }

}