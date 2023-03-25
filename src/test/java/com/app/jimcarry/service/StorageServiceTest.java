package com.app.jimcarry.service;


import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
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
    void getListBy() {
        int total = 0;
        Criteria criteria = new Criteria().create(1, 10);
        PageDTO pageDTO = null;
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(2L);

        total = storageService.getTotalBy(searchDTO);
        storageService.getListBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
    }

    @Test
    void getTotal() {
    }

    @Test
    void getTotalBy() {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(2L);
        log.info("getTotalby : " + storageService.getTotalBy(searchDTO));
    }
}
