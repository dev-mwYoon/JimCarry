package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /* 공지사항 전체목록 조회 */
    public List<NoticeVO> selectAll(@Param("page") PageDTO pageDTO);

    /* 공지사항 전체 갯수 조회*/
    public int total();

    /*공지사항 조회*/
    public NoticeVO select(Long noticeId);

    /* 공지사항 삭제*/
    public void delete(Long noticeId);
}
