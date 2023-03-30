package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.NoticeDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.vo.NoticeVO;
import com.app.jimcarry.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDAO noticeDAO;
    private final UserVO userVO;

    /* 공지사항 전체 목록 조회*/
    @LogStatus
    public List<NoticeVO> getList(PageDTO pageDTO){
        return noticeDAO.findAll(pageDTO);
    }

    /*공지사항 전체 개수*/
    @LogStatus
    public int getTotal(){return noticeDAO.findTotal();}

    @LogStatus
    public String sendRandomNumber(String userPhone) throws CoolsmsException {
        String api_key = "NCSW9JM1RREOSKPR";
        String api_secret = "CWPWKNOLA3D0FD94JOY4W6Q2SBYXVSOK";
        Message coolsms = new Message(api_key, api_secret);

//        Random random = new Random();
//        String numStr = "";
        String context = "안녕하세요 짐캐리입니다 답변이 완료되었습니다";

//        회원가입에서 -가 붙어서 오기 때문에 떼어주는것
       userPhone = userVO.getUserPhone().replace("-", "");

       /* for(int i = 0; i < 6; i++) {
            String number = Integer.toString(random.nextInt(10));
            numStr += number;
        }*/

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", userPhone);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
        params.put("from", "01029670403");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "sms");
        params.put("text", context);

        coolsms.send(params); // 메시지 전송

        return context;
    }

        /*조회*/
        @LogStatus
        public NoticeVO getNotice(Long noticeId) {return noticeDAO.findById(noticeId);}

        /* 공지사항 삭제 */
        public void removeNotice(Long noticeId){
            noticeDAO.deleteById(noticeId);
        }




}

