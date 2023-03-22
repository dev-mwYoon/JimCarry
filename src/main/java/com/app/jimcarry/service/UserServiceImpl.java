package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.Encryption;
import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.UserDAO;
import com.app.jimcarry.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 회원 관련 서비스, <br>
 *
 * @author 강민구
 * @since 2023/03/21
 */
@Service
@RequiredArgsConstructor
@Qualifier("mainUserService")
@Primary
/*추후에 클래스명 변경 요망*/
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    /**
     * 회원가입 서비스
     *
     * @param userVO 화면에서 입력받은 회원정보
     * @throws NoSuchElementException   회원정보VO NULL값 들어옴
     * @throws IllegalArgumentException 중복된 회원 아이디가 들어옴
     */
    @Override
    @LogStatus
    @Encryption /* 회원 비밀번호 암호화 Aspect */
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserVO userVO) {
//        userVO null 검사
        Optional.ofNullable(userVO).orElseThrow();
        String userIdentification = userVO.getUserIdentification();

        /* 아이디 중복검사 */
        if (checkIdentificationDuplicate(userIdentification)) {
            userDAO.save(userVO);
        } else throw new IllegalArgumentException("Duplicate Identification");
    }

    /**
     * 회원조회 서비스
     *
     * @param userId 화면에서 입력받은 회원번호
     * @throws NoSuchElementException 조회된 회원 없음
     */
    @Override
    @LogStatus
    public UserVO getUser(Long userId) {
        return Optional.ofNullable(userDAO.findById(userId)).get();
    }

    /**
     * 검색조건을 통한 회원조회 서비스
     *
     * @param search "types" 키값에 검색 항목을 넣고, <br>
     *               그 항목대로 키 : 밸류를 넣는다 <br>
     *               ex) types : new ArrayList<String>(Arrays.asList("userIdentification"))<br>
     *               map.put("userIdentification", [검색하고자 하는 내용])<br>
     *               주의  : userMapper.xml 에 types도 등록해야 함
     * @throws IllegalArgumentException keys 키값이 들어오지 않음
     */
    @Override
    @LogStatus
    public List<UserVO> getUserBy(Map<String, Object> search) {
        if (!search.containsKey("types")) {
            throw new IllegalArgumentException("types key not found");
        }
        return userDAO.findBy(search);
    }

    /**
     * 전체회원 조회 서비스
     */
    @Override
    @LogStatus
    public List<UserVO> getList() {
        return userDAO.findAll();
    }

    /**
     * 회원정보 업데이트 서비스
     *
     * @param userVO 화면에서 입력받은 회원정보
     */
    @Override
    @LogStatus
    public void updateUser(UserVO userVO) {
        userDAO.setUserVO(userVO);
    }

    /**
     * 회원정보 삭제 서비스
     *
     * @param userId 회원번호
     * @throws NoSuchElementException 해당 회원번호가 존재하지 않음
     */
    @Override
    @LogStatus
    public void removeUser(Long userId) {
        Optional.ofNullable(userDAO.findById(userId)).orElseThrow();
        userDAO.deleteById(userId);
    }

    /**
     * 로그인 서비스
     */
    @LogStatus
    @Encryption
    public Long login(String userIdentification, String userPassword) {
        return userDAO.login(userIdentification, userPassword);
    }


    /**
     * 회원 아이디 중복검사 메소드
     * 사용가능 = true, 사용불가 = false
     *
     * @param userIdentification 회원 아이디
     */
    @LogStatus
    public boolean checkIdentificationDuplicate(String userIdentification) {
//        사용가능 = true, 사용불가 = false
        return userDAO.findCountByUserIdentification(userIdentification) == 0;
    }
}
