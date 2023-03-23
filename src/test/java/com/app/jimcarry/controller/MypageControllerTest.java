package com.app.jimcarry.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@Transactional
class MypageControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void updateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/update/2")
                .param("userIdentification", "MypageControllerUpdateTest")
                .param("userPassword", "4321")
                .param("userEmail", "ControllerTest@gmail.com")
                .param("userPhone", "01043214321")
                .param("userAddress", "수원시 영통구 매탄동")
                .param("userAddressDetail", "상세주소")
                .param("userGender", "남")
                .param("userBirth", "1900-01-01")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/update/2")
                .param("userIdentification", "MypageControllerUpdateTest")
                .param("userPassword", "4321")
                .param("userEmail", "ControllerTest@gmail.com")
                .param("userPhone", "01043214321")
                .param("userAddress", "수원시 영통구 매탄동")
                .param("userAddressDetail", "상세주소")
                .param("userGender", "남")
                .param("userBirth", "1900-01-01")
        ).andExpect(redirectedUrl("/errors/400"));
    }
}