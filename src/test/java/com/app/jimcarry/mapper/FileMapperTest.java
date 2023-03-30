package com.app.jimcarry.mapper;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
public class FileMapperTest {

    @Autowired
    FileMapper fileMapper;

    /*조회*/
    @Test
    public void select(){
        fileMapper.select(1L);
    }
}
