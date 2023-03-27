package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    /* 리뷰 추가 */
    public void insert(ReviewVO reviewVO);

    /* 리뷰 전체 목록 조회*/
    public List<ReviewDTO> selectAll(PageDTO pageDTO);

    /* 리뷰 목록 개수 조회*/
    public int total();

    /* 한 창고의 리뷰 조회 */
    public List<ReviewDTO> selectByStorageId(Long storageId);

    /* 한 창고의 리뷰 개수 조회*/
    public int totalById(Long storageId);
}
