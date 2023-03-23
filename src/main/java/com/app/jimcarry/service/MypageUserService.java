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

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
public class MypageUserService implements UserService {
    private final UserDAO userDAO;

    /**
     * 회원가입 서비스 (사용안함)
     */
    @Override
    public void registerUser(UserVO userVO) {;}

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
     * 회원 이전 비밀번호 검사 서비스
     * 사용가능 = true, 사용불가 = false
     *
     */
    @LogStatus
    @Encryption
    public boolean checkFormerPassword(Long userId, String userPassword) {
        return userDAO.findById(userId).getUserPassword().equals(userPassword);
    }
}
