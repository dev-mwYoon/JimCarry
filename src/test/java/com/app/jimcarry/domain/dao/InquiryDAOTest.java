package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.FileVO;
import com.app.jimcarry.domain.vo.InquiryFileVO;
import com.app.jimcarry.domain.vo.InquiryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
public class InquiryDAOTest {

    @Autowired
        InquiryDAO inquiryDAO;
    @Autowired
        InquiryVO inquiryVO;
    @Autowired
        InquiryFileVO inquiryFileVO;
    @Autowired
        FileVO fileVO;
}
