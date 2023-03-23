package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author 강민구
 * @since 2023/03/21
 * */
@SpringBootTest
@Slf4j
@Transactional
class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserVO userVO;

    @BeforeEach
    void setUserVO() {
        userVO.setUserIdentification("userMapperTest");
        userVO.setUserPassword("1234");
        userVO.setUserEmail("userMapperTest@gmail.com");
        userVO.setUserPhone("01012341234");
        userVO.setUserAddress("서울시 강남구 역삼동");
        userVO.setUserAddressDetail("경리단길 123");
        /*userVO.setUserGender(null);*/ // 선택안함
        userVO.setUserBirth("1900-01-01");
    }

    @Test
    void insert() {
        /* insert 시 userId 받아오는가 테스트 */
//        userMapper.insert(userVO);
//        assertThat(userVO.getUserId()).isNotNull();
//        log.info(userVO.getUserId().toString());
        /* ============================== */

        /* insert 정상작동 테스트 */
        assertDoesNotThrow(() -> userMapper.insert(userVO));
        assertThrows(Exception.class, () -> userMapper.insert(null));
        /* ============================== */
    }

    @Test
    void select() {
        userMapper.insert(userVO);
        assertThat(userMapper.select(userVO.getUserId()).getUserId())
                .isEqualTo(userVO.getUserId());
    }

    @Test
    void selectAllBy() {
        String test = "Test";
        userMapper.insert(userVO);

        Map<String, Object> map = new HashMap<>();
        map.put("types", new ArrayList<String>(Arrays
                .asList("userIdentification")));
        map.put("userIdentification", test);

        userMapper.selectAllBy(map).forEach(user -> assertThat(user.getUserIdentification()).contains(test));
    }

    @Test
    void selectAll() {
        userMapper.insert(userVO);
        assertThat(userMapper.selectAll().size()).isGreaterThan(0);
    }

    @Test
    void update() {
        String update = "updateduserMapperTest";
        userMapper.insert(userVO);
        userVO.setUserIdentification(update);
        userMapper.update(userVO);
        assertThat(userMapper.select(userVO.getUserId()).getUserIdentification()).isEqualTo(update);
    }

    @Test
    void delete() {
        userMapper.insert(userVO);
        userMapper.delete(userVO.getUserId());
        assertThat(userMapper.select(userVO.getUserId())).isNull();
    }

    @Test
    void selectByIdentificationAndPassword() {
        userMapper.insert(userVO);
        assertThat(userMapper.selectByIdentificationAndPassword("userMapperTest", "1234")).isEqualTo(userVO.getUserId());
//        assertThat(userMapper.selectByIdentificationAndPassword("userServiceDuplicateTest", "MTIzNA==")).isNotNull();
    }

    @Test
    void selecBytIdentification() {
        userMapper.insert(userVO);
        assertThat(userMapper.selecBytIdentification(userVO.getUserIdentification())).isGreaterThan(0);
    }
}