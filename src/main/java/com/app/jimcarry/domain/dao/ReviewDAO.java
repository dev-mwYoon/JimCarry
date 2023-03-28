package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.ReviewVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewDAO {

    private final ReviewMapper reviewMapper;

    /* 리뷰 추가 */

    /* 리뷰 조회 */
    public ReviewVO findById(Long reviewId) { return reviewMapper.select(reviewId); }

    /* 리뷰 목록 조회*/
    public List<ReviewDTO> findAll(PageDTO pageDTO) {
        return reviewMapper.selectAll(pageDTO);
    }

    /* 목록 개수 조회*/
    public int findTotal() {
        return reviewMapper.total();
    }

    /* 한 창고의 리뷰 조회 */
    public List<ReviewDTO> findByStorageId(Long storageId) {
        return reviewMapper.selectByStorageId(storageId);
    };

    /* 한 창고의 리뷰 개수 조회*/
    public int getTotalById(Long storageId){
        return reviewMapper.totalById(storageId);
    };

    /* 리뷰 조건조회 */
    public List<ReviewDTO> findAllBy(PageDTO pageDTO){
        return reviewMapper.selectAllBy(pageDTO);
    }

    /* 리뷰 조건조회 개수 */
    public int getTotalBy(SearchDTO searchDTO){
        return reviewMapper.totalBy(searchDTO);
    }

    /* 리뷰 업데이트 */
    public void setReviewVO(ReviewVO reviewVO) { reviewMapper.update(reviewVO); }
}
