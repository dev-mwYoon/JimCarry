package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.ReviewFileDTO;
import com.app.jimcarry.domain.vo.ReviewFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewFileMapper {

    //    등록
    public void insert(ReviewFileVO reviewFileVO);

    //    파일 전체 조회
    public List<ReviewFileVO> selectAll(Long reviewId);

    //    삭제
    public void delete(Long reviewId);
    
    /* 창고 아이디로 조회 */
    public List<ReviewFileVO> selectByReviewId(Long reviewId);
}
