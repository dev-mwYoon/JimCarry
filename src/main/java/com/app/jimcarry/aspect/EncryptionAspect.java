package com.app.jimcarry.aspect;

import com.app.jimcarry.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.Optional;

/**
 * 비밀번호 암호화 Aspect, <br>
 * 제네릭 타입으로 매개변수 1개(UserVO)일 떈 null, 매개변수 2개일 떄에는 해당 회원번호 리턴
 *
 * @author 강민구
 * @since 2023/03/21
 */
@Aspect
@Configuration
@Slf4j
public class EncryptionAspect<T> {

    /**
     * @throws IllegalArgumentException 매개변수 3개 이상의 함수에 사용했을 떄
     */
    @Around(value = "@annotation(com.app.jimcarry.aspect.annotation.Encryption)")
    public T around(ProceedingJoinPoint joinPoint) throws Throwable {

        int argsLenghth = joinPoint.getArgs().length;
        if (argsLenghth == 1) {
            //  userVO일 경우
            UserVO userVO = ((UserVO) joinPoint.getArgs()[0]);
            Optional.ofNullable(userVO).orElseThrow();
            userVO.setUserPassword(encryptPassword(userVO.getUserPassword()));
            joinPoint.proceed(new Object[]{userVO});

//            return userVO.getUserId();
            return null;

        } else if (argsLenghth == 2) {
            //  userVO가 아닌 passoword 가 전달될 경우
            String password = String.valueOf(joinPoint.getArgs()[1]);
            Object[] test = new Object[]{joinPoint.getArgs()[0].toString(), encryptPassword(password)};
            return (T) joinPoint.proceed(new Object[]{joinPoint.getArgs()[0].toString(), encryptPassword(password)});

            // userVO도 아니고 password도 아닐 경우
        } else throw new IllegalArgumentException("too many arguments");
    }

    /**
     * 비밀번호 base64기반 암호화 메소드
     * */
    private String encryptPassword(String arg) {
        return new String(Base64.getEncoder().encode(arg.getBytes()));
    }
}
