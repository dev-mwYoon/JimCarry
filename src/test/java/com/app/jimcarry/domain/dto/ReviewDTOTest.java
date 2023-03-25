package com.app.jimcarry.domain.dto;

import com.app.jimcarry.domain.vo.ReviewDTO;
import com.app.jimcarry.mapper.ReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReviewDTOTest {

    @Autowired
    PageDTO pageDTO;

    @Autowired
    ReviewDTO reviewDTO;

    @Autowired
    ReviewMapper reviewMapper;

    @Test
    public void listTest(){
        log.info(reviewMapper.selectAll(pageDTO).toString());
    }
}
