package com.app.jimcarry.service;


import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
@Transactional
public class StorageServiceTest {

    @Autowired
    private StorageService storageService;

    @Test
    void register() {
    }

    @Test
    void getStorage() {
    }

    @Test
    void getList() {
    }

    @Test
    void getListByUserId() {
        int total = storageService.getTotal();
        Criteria criteria = new Criteria().create(1, 10);
        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total);
//        pageDTO.setTypes(new ArrayList<>(Arrays.asList("userId", "keyword")));
//        pageDTO.setKeyword("keyword");
//        pageDTO.setUserId(1L);
        storageService.getListBy(pageDTO);
    }
}
