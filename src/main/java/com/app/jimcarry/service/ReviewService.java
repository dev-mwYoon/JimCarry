package com.app.jimcarry.service;

import com.app.jimcarry.domain.dao.ReviewDAO;
import com.app.jimcarry.domain.dao.ReviewFileDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.ReviewFileVO;
import com.app.jimcarry.domain.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDAO reviewDAO;
    private final ReviewFileDAO reviewFileDAO;

    /* 리뷰 조회 */
    public ReviewVO getReview(Long reviewId) {
        return reviewDAO.findById(reviewId);
    }

    /* 리뷰 전체 목록 조회*/
    public List<ReviewDTO> getList(PageDTO pageDTO) {
        return reviewDAO.findAll(pageDTO);
    }

    /*전체개수 조회*/
    public int getTotal() {
        return reviewDAO.findTotal();
    }

    /* 한 창고의 리뷰 조회 */
    public List<ReviewDTO> getListByStorageId(Long storageId) {
        return reviewDAO.findByStorageId(storageId);
    }

    /* 한 창고의 리뷰 개수 조회*/
    public int getTotalById(Long storageId) {
        return reviewDAO.getTotalById(storageId);
    }

    ;

    /* 리뷰 조건조회 */
    public List<ReviewDTO> getListBy(PageDTO pageDTO) {
        return reviewDAO.findAllBy(pageDTO);
    }

    /* 리뷰 조건조회 개수 */
    public int getTotalBy(SearchDTO searchDTO) {
        return reviewDAO.getTotalBy(searchDTO);
    }

    /* 리뷰 업데이트 */
    @Transactional(rollbackFor = Exception.class)
    public void updateReview(ReviewDTO reviewDTO) {
        reviewDAO.setReviewVO(reviewDTO.createVO());

        /* 들어온 파일이 아예 없을 때 */
        if(reviewDTO.getFiles().size() < 1) {
            return;
        }
        reviewFileDAO.deleteById(reviewDTO.getReviewId());
        reviewDTO.getFiles().stream().map(file -> new ReviewFileVO().create(file, reviewDTO.getReviewId()))
                .forEach(file -> { file.setFilePath(getPath()); reviewFileDAO.save(file); });

    }

    /* 리뷰 저장 */
    @Transactional(rollbackFor = Exception.class)
    public void registerReview(ReviewDTO reviewDTO) {
        ReviewVO newReview = reviewDTO.createVO();
        reviewDAO.save(newReview);
        reviewDTO.getFiles().stream().map(file -> new ReviewFileVO().create(file, newReview.getReviewId()))
                .forEach(file -> { file.setFilePath(getPath()); reviewFileDAO.save(file); });
    }

    public void removeReview(Long reviewId) {
        reviewDAO.deleteById(reviewId);
    }

    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
