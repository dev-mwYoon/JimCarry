package com.app.jimcarry.mapper;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /* 공지사항 전체목록 조회 */
    public List<NoticeVO> selectAll(PageDTO pageDTO);

    /* 공지사항 전체 갯수 조회*/
    public int total();
}
