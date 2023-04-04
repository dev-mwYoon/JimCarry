package com.app.jimcarry.domain.dao;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.NoticeVO;
import com.app.jimcarry.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;
    private final  NoticeVO noticeVO;

    /* 공지사항 전체목록 조회 */
    public List<NoticeVO> findAll(PageDTO pageDTO){
        return noticeMapper.selectAll(pageDTO);
    }
    public List<NoticeVO> findAllBy(PageDTO pageDTO){
        return noticeMapper.selectAllBy(pageDTO);
    }


    /* 공지사항 전체 개수 조회*/
    public int findTotal(){return noticeMapper.total();}
    public int findTotalBy(SearchDTO searchDTO){return noticeMapper.totalBy(searchDTO);}


    /*공지사항 조회*/
    public NoticeVO findById(Long noticeId) { return noticeMapper.select(noticeId);}

    /* 공지사항 삭제*/
    public void deleteById(Long noticeId){
        noticeMapper.delete(noticeId);
    }

    /*공지사항 수정*/
    public void updateById(NoticeVO noticeVO){ noticeMapper.update(noticeVO);}

    /*공지사항 작성*/
    public void save(NoticeVO noticeVO){noticeMapper.insert(noticeVO);}
}
