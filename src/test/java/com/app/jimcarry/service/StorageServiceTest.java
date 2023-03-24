package com.app.jimcarry.service;


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
        Map<String, Object> map = new HashMap<>();
        Criteria criteria = new Criteria().create(2, 10);
        map.put("types", new ArrayList<>(Arrays.asList("userId")));
        map.put("userId", 1L);
        map.put("startRow", criteria.getStartRow());
        map.put("amount", criteria.getAmount());
        storageService.getListBy(map);
    }
}
