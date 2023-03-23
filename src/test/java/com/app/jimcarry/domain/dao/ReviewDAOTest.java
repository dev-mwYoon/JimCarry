package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
public class ReviewDAOTest {

    @Autowired
    ReviewDAO reviewDAO;

    @Autowired
    ReviewVO reviewVO;



}