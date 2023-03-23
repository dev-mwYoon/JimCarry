package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    //    추가
    public void insert(UserVO userVO);

    //    조회
    public UserVO select(Long userId);

    //    조건조회
    public List<UserVO> selectAllBy(Map<String, Object> search);

    //    아이디, 비밀번호로 조회(로그인)
    public Long selectByIdentificationAndPassword(String userIdentification, String userPassword);

    //    아이디 갯수 조회(중복검사)
    public int selectByIdentification(String userIdentification);

    public int selectByEmail(String userEmail);

    //    목록
    public List<UserVO> selectAll();

    //    수정
    public void update(UserVO userVO);

    //    삭제
    public void delete(Long userId);

}
