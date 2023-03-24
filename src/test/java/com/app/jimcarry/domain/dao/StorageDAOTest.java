package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StorageDAOTest {

    @Autowired
    StorageDAO storageDAO;
    @Autowired
    StorageVO storageVO;

    @BeforeEach
    void setStorageVO(){
        storageVO.setUserId(1L);
        storageVO.setStorageTitle("storageMapperTest");
        storageVO.setStorageSize("특대");
        storageVO.setStoragePrice(30000);
        storageVO.setStorageAddress("서울시 강남구 역삼동");
        storageVO.setStorageAddressDetail("상세주소");
        storageVO.setStorageUseDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        storageVO.setStorageEndDate("2099/12/31");
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findBy() {
        int total = storageDAO.findTotal();
        Criteria criteria = new Criteria().create(1, 10);
        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total);
        pageDTO.setTypes(new ArrayList<>(Arrays.asList("userId", "keyword")));
        pageDTO.setKeyword("keyword");
        pageDTO.setUserId(1L);
        storageDAO.findBy(pageDTO);
    }

    @Test
    void findTotal() {
        assertThat(storageDAO.findTotal()).isGreaterThan(0);
    }
}