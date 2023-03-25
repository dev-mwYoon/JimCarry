package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewDAO {

    private final ReviewMapper reviewMapper;

    /* 리뷰 추가 */

    /* 리뷰 조회 */

    /* 리뷰 목록 조회*/
    public List<ReviewDTO> findAll(PageDTO pageDTO) {
        return reviewMapper.selectAll(pageDTO);
    }

    /* 목록 개수 조회*/
    public int findTotal() {
        return reviewMapper.total();
    }
}
