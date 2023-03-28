package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.ReviewDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDAO reviewDAO;

    /* 리뷰 조회 */
    public ReviewVO getReview(Long reviewId) { return reviewDAO.findById(reviewId); }

    /* 리뷰 전체 목록 조회*/
    public List<ReviewDTO> getList(PageDTO pageDTO){
        return reviewDAO.findAll(pageDTO);
    }

    /*전체개수 조회*/
    public int getTotal() {
        return reviewDAO.findTotal();
    }

    /* 한 창고의 리뷰 조회 */
    public List<ReviewDTO> getListByStorageId(Long storageId){
        return reviewDAO.findByStorageId(storageId);
    }

    /* 한 창고의 리뷰 개수 조회*/
    public int getTotalById(Long storageId){
        return reviewDAO.getTotalById(storageId);
    };

    /* 리뷰 조건조회 */
    public List<ReviewDTO> getListBy(PageDTO pageDTO){
        return reviewDAO.findAllBy(pageDTO);
    }

    /* 리뷰 조건조회 개수 */
    public int getTotalBy(SearchDTO searchDTO){
        return reviewDAO.getTotalBy(searchDTO);
    }

    /* 리뷰 업데이트 */
    public void updateReview(ReviewVO reviewVO) {
        Optional.ofNullable(reviewDAO.findById(reviewVO.getReviewId()));
        reviewDAO.setReviewVO(reviewVO);
    }

    /* 리뷰 저장 */
    @Transactional(rollbackFor = Exception.class)
    public void registerReview(ReviewVO reviewVO){
        reviewDAO.save(reviewVO);
    }
}
