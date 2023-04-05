package com.app.jimcarry.aspect;

import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.PaymentService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
@RequiredArgsConstructor
public class MypageControllerAspect {

    private final HttpServletRequest request;
    private final StorageService storageService;
    private final PaymentService paymentService;

    @Before("@annotation(com.app.jimcarry.aspect.annotation.MypageHeaderValue)")
    public void setHeaderInfoValue(JoinPoint joinPoint) throws Throwable{
        Object user = request.getSession().getAttribute("user");
        if(user == null) {
            return;
        }

        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
        searchDTO.setUserId(((UserVO)user).getUserId());
        int storageTotal = storageService.getTotalBy(searchDTO);
        int paymentTotal = paymentService.getTotalBy(searchDTO);
        request.setAttribute("storageTotal", storageTotal);
        request.setAttribute("paymentTotal", paymentTotal);
    }
}
