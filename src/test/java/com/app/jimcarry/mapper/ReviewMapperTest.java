package com.app.jimcarry.mapper;


import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
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
    ReviewMapper reviewMapper;

    @Test
    public void listTest(){
        log.info(reviewMapper.selectAll().toString());
    }

}
