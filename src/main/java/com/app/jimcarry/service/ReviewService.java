package com.app.jimcarry.service;

import com.app.jimcarry.domain.dao.ReviewDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDAO reviewDAO;

    /* 리뷰 전체 목록 조회*/
    public List<ReviewDTO> getList(PageDTO pageDTO){
        return reviewDAO.findAll(pageDTO);
    }
}
