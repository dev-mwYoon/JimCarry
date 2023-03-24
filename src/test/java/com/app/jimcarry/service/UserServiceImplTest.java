package com.app.jimcarry.service;

import com.app.jimcarry.domain.vo.Criteria;
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
    UserService userService;
    @Autowired
    UserVO userVO;

    @BeforeEach
    void setUserVO() {
        userVO.setUserIdentification("MypageUserServiceTest");
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
        assertDoesNotThrow(() -> {
            userService.registerUser(userVO);
        });
        userVO.setUserIdentification("userServiceTest");
        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(userVO);
        });
        assertThrows(NoSuchElementException.class, () -> {
            userService.registerUser(null);
        });
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
        Criteria criteria = new Criteria().create(1, 10);
        userService.registerUser(userVO);
        assertThat(userService.getList(criteria).size()).isGreaterThan(0);
    }

    @Test
    void updateUser() {
        String update = "updateduserMapperTest";
        String updatePassword = "4321";
        userService.registerUser(userVO);
        userVO.setUserIdentification(update);
        userVO.setUserPassword(updatePassword);
        userService.updateUser(userVO);
        assertThat(userService.getUser(userVO.getUserId()).getUserIdentification()).isEqualTo(update);
        log.info(userService.getUser(userVO.getUserId()).getUserPassword());
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
    void checkEmailDuplicate() {
        userService.registerUser(userVO);
        assertThat(userService.checkEmailDuplicate(userVO.getUserEmail())).isFalse();
    }
}