package com.app.jimcarry.service;

import com.app.jimcarry.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author 강민구
 * @since 2023/03/21
* */
@SpringBootTest
@Slf4j
//@Transactional
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserVO userVO;

    @BeforeEach
    void setUserVO() {
        userVO.setUserIdentification("userServiceTest");
        userVO.setUserPassword("1234");
        userVO.setUserEmail("userMapperTest@gmail.com");
        userVO.setUserPhone("01012341234");
        userVO.setUserAddress("서울시 강남구 역삼동");
        userVO.setUserAddressDetail("경리단길 123");
        userVO.setUserGender(null); // 선택안함
        userVO.setUserBirth("1900-01-01");
    }

    @Test
    void registerUser() {
//        assertDoesNotThrow(() -> {
//            userService.registerUser(userVO);
//        });
//        userVO.setUserIdentification("userServiceTest");
//        assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(userVO);
//        });
//        assertThrows(NoSuchElementException.class, () -> {
//            userService.registerUser(null);
//        });
        userService.registerUser(userVO);
    }

    @Test
    void getUser() {
        userService.registerUser(userVO);
        final Long userId = userVO.getUserId();
        assertThat(userService.getUser(userId).getUserId()).isEqualTo(userId);
        userService.removeUser(userId);
        assertThrows(NoSuchElementException.class, () -> userService.getUser(userId));
    }

    @Test
    void getUserBy() {
        Map<String, Object> search = new HashMap<>();
        String matchName = "Test";
        String unMatchName = "*^@!*###";

        userService.registerUser(userVO);

        search.put("types", new ArrayList<String>(Arrays.asList("userIdentification")));
        search.put("keyword", matchName);

        userService.getUserBy(search).forEach(user -> assertThat(user.getUserIdentification()).contains(matchName));
    }

    @Test
    void getList() {
        userService.registerUser(userVO);
        assertThat(userService.getList().size()).isGreaterThan(0);
    }

    @Test
    void updateUser() {
        String update = "updateduserMapperTest";
        userService.registerUser(userVO);
        userVO.setUserIdentification(update);
        userService.updateUser(userVO);
        assertThat(userService.getUser(userVO.getUserId()).getUserIdentification()).isEqualTo(update);
    }

    @Test
    void removeUser() {
        userService.registerUser(userVO);
        Long userId = userVO.getUserId();
        assertDoesNotThrow(() -> {
            userService.removeUser(userId);
        });
        assertThrows(NoSuchElementException.class, () -> {
            userService.removeUser(userId);
        });
    }

    @Test
    void login() {
        userService.registerUser(userVO);
        assertThat(userService.login("userServiceTest", "1234")).isEqualTo(userVO.getUserId());
    }

    @Test
    void checkIdentificationDuplicate() {
//        userService.registerUser(userVO);
//        assertThat(userService.checkIdentificationDuplicate(userVO.getUserIdentification())).isFalse();
        assertThat(userService.checkIdentificationDuplicate("userServiceTest")).isEqualTo(false);
    }
}