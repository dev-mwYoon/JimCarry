package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //    추가
    public void insert(UserVO userVO);

    //    조회
    public UserVO select(Long userId);

    //    조건검색 개수조회
    public List<UserVO> selectAllBy(@Param("page") PageDTO pageDTO);

    //    조건검색 개수조회
    public int totalBy(@Param("page") SearchDTO searchDTO);

    //    아이디, 비밀번호로 조회(로그인)
    public Long selectByIdentificationAndPassword(String userIdentification, String userPassword);

    //    아이디 갯수 조회(중복검사)
    public int selectByIdentification(String userIdentification);

    //    이메일 중복검사
    public int selectByEmail(String userEmail);

    //    목록
    public List<UserVO> selectAll(@Param("page") PageDTO pageDTO);

    //    목록 개수 조회
    public int total();

    //    수정
    public void update(UserVO userVO);

    //    삭제
    public void delete(Long userId);

    //    이름, 핸드폰 번호로 검색
    public UserVO selectByNameAndPhone(String userName, String userPhone);

    //    비밀번호 변경
    public void updatePasswordByIdentification(String userIdentification, String userPassword);
}
