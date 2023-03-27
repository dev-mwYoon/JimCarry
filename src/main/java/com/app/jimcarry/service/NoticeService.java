package com.app.jimcarry.service;

import com.app.jimcarry.domain.dao.NoticeDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDAO noticeDAO;

    /* 공지사항 전체 목록 조회*/
    public List<NoticeVO> getList(PageDTO pageDTO){
        return noticeDAO.findAll(pageDTO);
    }

    /*공지사항 전체 개수*/
    public int getTotal(){return noticeDAO.findTotal();}

}

