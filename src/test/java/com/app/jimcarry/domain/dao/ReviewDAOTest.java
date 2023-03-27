package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /* 한 창고의 리뷰 조회 */
    @Test
    public void findByStorageId(){
        reviewDAO.findByStorageId(8L);
    }

    /* 한 창고의 리뷰 개수 조회*/
    @Test
    public void getTotalByStorageId() {
        reviewDAO.getTotalById(8L);
    }
}