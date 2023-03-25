package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.Encryption;
import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.UserDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
/*추후에 클래스명 변경 요망*/
public class UserService {
    private final UserDAO userDAO;

    /**
     * 회원가입 서비스
     *
     * @param userVO 화면에서 입력받은 회원정보
     * @throws NoSuchElementException   회원정보VO NULL값 들어옴
     * @throws IllegalArgumentException 중복된 회원 아이디가 들어옴
     */
    @LogStatus
    @Encryption /* 회원 비밀번호 암호화 Aspect */
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserVO userVO) {
//        userVO null 검사
        Optional.ofNullable(userVO).orElseThrow();
        String userIdentification = userVO.getUserIdentification();

        if(userVO.getUserGender() == null) {
            userVO.setUserGender("선택 안함");
        }

        userVO.setUserBirth(userVO.getUserBirth().replace(",", "-"));

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
    @LogStatus
    public List<UserVO> getUserBy(Map<String, Object> search) {
        if (!search.containsKey("types")) {
            throw new IllegalArgumentException("types key not found");
        }
        return /*userDAO.findListBy(pageDTO)*/null;
    }

    /**
     * 전체회원 조회 서비스
     *
     * @param pageDTO 화면에서 받은 페이징 정보
     */
    @LogStatus
    public List<UserVO> getList(PageDTO pageDTO) {
        return userDAO.findAll(pageDTO);
    }

    /**
     * 회원정보 업데이트 서비스
     *
     * @param userVO 화면에서 입력받은 회원정보
     */
    @Encryption
    @LogStatus
    @Transactional
    public void updateUser(UserVO userVO) {
        userDAO.setUserVO(userVO);
    }

    /**
     * 회원정보 삭제 서비스
     *
     * @param userId 회원번호
     * @throws NoSuchElementException 해당 회원번호가 존재하지 않음
     */
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
        //     사용가능 = true, 사용불가 = false
        return userDAO.findCountByUserIdentification(userIdentification) == 0;
    }

    /**
     * 회원 이메일 중복검사 메소드
     * 사용가능 = true, 사용불가 = false
     *
     * @param userEmail 회원 이메일
     */
    @LogStatus
    public boolean checkEmailDuplicate(String userEmail) {
        //     사용가능 = true, 사용불가 = false
        log.info("checkEmailDuplicate : " + (userDAO.findCountByUserEmail(userEmail) == 0));
        return userDAO.findCountByUserEmail(userEmail) == 0;
    }


    public String sendRandomNumber(String userPhone) throws CoolsmsException {
//        String api_key = "NCSW9JM1RREOSKPR";
//        String api_secret = "CWPWKNOLA3D0FD94JOY4W6Q2SBYXVSOK";
//        Message coolsms = new Message(api_key, api_secret);

        Random random = new Random();
        String numStr = "";

//        회원가입에서 -가 붙어서 오기 때문에 떼어주는것
        userPhone = userPhone.replace("-", "");

        for(int i = 0; i < 6; i++) {
            String number = Integer.toString(random.nextInt(10));
            numStr += number;
        }

//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("to", userPhone);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
//        params.put("from", "01022876873");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
//        params.put("type", "sms");
//        params.put("text", "인증번호는 [" + numStr + "] 입니다.");
//
//        coolsms.send(params); // 메시지 전송

        return numStr;
    }
}
