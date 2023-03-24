package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class UserDAOTest {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserVO userVO;

    @BeforeEach
    void beforeEachSetUserVO() {
        userVO.setUserIdentification("userMapperTest");
        userVO.setUserPassword("1234");
        userVO.setUserEmail("userMapperTest@gmail.com");
        userVO.setUserPhone("01012341234");
        userVO.setUserAddress("서울시 강남구 역삼동");
        userVO.setUserAddressDetail("경리단길 123");
        userVO.setUserGender(null); // 선택안함
        userVO.setUserBirth("1900-01-01");
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findBy() {
    }

    @Test
    void findAll() {
        Criteria criteria = new Criteria().create(1, 10);
        List<UserVO> users = userDAO.findAll(criteria);

        if (users.size() == 0) {
            assertThat(users.size()).isEqualTo(0);
        } else {
            users.forEach(user -> assertThat(user).isInstanceOf(UserVO.class));
        }
    }

    @Test
    void setUserVO() {
        String test = "testUpdate";
        Optional.ofNullable(userDAO.findById(1L)).ifPresent(user -> {
            String id = user.getUserIdentification();
            user.setUserIdentification(test);
            userDAO.setUserVO(user);

            assertThat(Optional.ofNullable(userDAO.findById(user.getUserId())).get().getUserIdentification()).isNotEqualTo(id);
        });
    }

    @Test
    void deleteById() {
        userDAO.deleteById(1L);
        assertThatThrownBy(() -> {
            Optional.ofNullable(userDAO.findById(1L)).get();
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void login() {
        log.info(String.valueOf(userDAO.login("userServiceDuplicateTest", "MTIzNA==")));
        assertThat(userDAO.login("userServiceDuplicateTest", "MTIzNA==")).isNotNull();
    }

    @Test
    void findCountByUserIdentification() {
    }

    @Test
    void findCountByUserEmail() {
        userDAO.save(userVO);
        assertThat(userDAO.findCountByUserEmail(userVO.getUserEmail())).isGreaterThan(0);
    }
}