package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.NoticeVO;
import com.app.jimcarry.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;

    /* 공지사항 전체목록 조회 */
    public List<NoticeVO> findAll(PageDTO pageDTO){
        return noticeMapper.selectAll(pageDTO);
    }

    /* 공지사사항 전체 개수 조회*/
    public int findTotal(){return noticeMapper.total();}
}
