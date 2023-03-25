package com.app.jimcarry.service;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.mapper.ReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    PageDTO pageDTO;

    /*리뷰목록조회*/

}
