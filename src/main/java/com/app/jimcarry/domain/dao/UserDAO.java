package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    //    키워드 조회
    public List<UserVO> findBy(Map<String, Object> search) {
        return userMapper.selectAllBy(search);
    }

    //    아이디, 비밀번호로 조회(로그인)
    public Long login(String userIdentification, String userPassword){
        return userMapper.selectByIdentificationAndPassword(userIdentification, userPassword);
    }

    //    아이디 갯수 조회(중복검사)
    public int findCountByUserIdentification(String userIdentification) {
        return userMapper.selecBytIdentification(userIdentification);
    }

    //    목록
    public List<UserVO> findAll() {
        return userMapper.selectAll();
    }

    //    수정
    public void setUserVO(UserVO userVO) {
        userMapper.update(userVO);
    }

    //    삭제
    public void deleteById(Long userId) {
        userMapper.delete(userId);
    }
}
