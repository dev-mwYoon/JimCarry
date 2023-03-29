package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDAO {
    private final UserMapper userMapper;

    //    추가
    public void save(UserVO userVO) {
        userMapper.insert(userVO);
    }

    //    조회
    public UserVO findById(Long userId) {
        return userMapper.select(userId);
    }

    //    조건검색
    public List<UserVO> findListBy(PageDTO pageDTO) {
        return userMapper.selectAllBy(pageDTO);
    }

    //    조건검색 개수조회
    public int findTotalBy(SearchDTO searchDTO) {
        return userMapper.totalBy(searchDTO);
    }

    //    아이디, 비밀번호로 조회(로그인)
    public UserVO login(String userIdentification, String userPassword) {
        return userMapper.selectByIdentificationAndPassword(userIdentification, userPassword);
    }

    //    아이디 갯수 조회(중복검사)
    public int findCountByUserIdentification(String userIdentification) {
        return userMapper.selectByIdentification(userIdentification);
    }

    //    이메일 중복검사
    public int findCountByUserEmail(String userEmail) {
        return userMapper.selectByEmail(userEmail);
    }

    //    목록
    public List<UserVO> findAll(PageDTO pageDTO) {
        return userMapper.selectAll(pageDTO);
    }

    //    목록 개수 조회
    public int findTotal() {
        return userMapper.total();
    }

    //    수정
    public void setUserVO(UserVO userVO) {
        userMapper.update(userVO);
    }

    //    삭제
    public void deleteById(Long userId) {
        userMapper.delete(userId);
    }

    //    이름, 핸드폰 번호로 아이디 찾기
    public UserVO findByPhoneAndNameOrIdentification(String userIdentification, String userName, String userPhone) {
        return userMapper.selectByPhoneAndNameOrIdentification(userIdentification, userName, userPhone);
    }

    //    이름, 이메일로 찾기
    public UserVO findByEmailAndNameOrIdentification(String userIdentification, String userName, String userEmail) {
        return userMapper.selectByEmailAndNameOrIdentification(userIdentification, userName, userEmail);
    }

    //    비밀번호 변경
    public void setPasswordByIdentification(String userIdentification, String userPassword) {
        userMapper.updatePasswordByIdentification(userIdentification, userPassword);
    }
    public int findUserTotal(){
        return userMapper.selectTotalUser();
    }

    //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
    //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
    public void setUserRandomKeyByIdentification(String userIdentification, String userRandomKey) {
        userMapper.updateRandomKeyByUserIdentification(userIdentification, userRandomKey);
    }

    //    아이디로 랜덤키 찾기
    public UserVO findByIdentificationUser(String userIdentification, String userEmail) {
        return userMapper.selectByIdentificationUser(userIdentification, userEmail);
    }
}
