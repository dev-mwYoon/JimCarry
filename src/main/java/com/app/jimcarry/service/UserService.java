package com.app.jimcarry.service;

import com.app.jimcarry.domain.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    //    추가
    public void registerUser(UserVO userVO);

    //    조회
    public UserVO getUser(Long userId);

    //    키워드 조회
    public List<UserVO> getUserBy(Map<String, Object> search);

    //    목록
    public List<UserVO> getList();

    //    수정
    public void updateUser(UserVO userVO);

    //    삭제
    public void removeUser(Long userId);
}
