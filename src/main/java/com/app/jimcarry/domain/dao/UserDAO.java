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
    public Long login(String userIdentification, String userPassword) {
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
    public String findByNameAndPhone(String userName, String userPhone) {
        return userMapper.selectByNameAndPhone(userName, userPhone);
    }
}
