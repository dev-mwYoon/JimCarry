package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.vo.InquiryFileVO;
import com.app.jimcarry.domain.vo.ReviewFileVO;
import com.app.jimcarry.mapper.InquiryFileMapper;
import com.app.jimcarry.mapper.ReviewFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewFileDAO {
    private final ReviewFileMapper reviewFileMapper;

    //    등록
    public void save(ReviewFileVO reviewFileVO) {
        reviewFileMapper.insert(reviewFileVO);
    }

    //    파일 전체 조회
    public List<ReviewFileVO> findAll(Long reviewId) {
        return reviewFileMapper.selectAll(reviewId);
    }

    //    삭제
    public void deleteById(Long reviewId) {
        reviewFileMapper.delete(reviewId);
    }
}