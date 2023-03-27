package com.app.jimcarry.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 로그 출력 Aspect
 */
@Aspect
@Configuration
@Slf4j
public class LogAspect {

    @Before("@annotation(com.app.jimcarry.aspect.annotation.LogStatus)")
    public void beforeStart(JoinPoint joinPoint) throws IOException {

        String path = "C:/upload/logs/" + getPath() + "/";
        String fileName = "log.txt";
        String log = "";

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(path, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        log += "[ " + getTime() + " ]" + "[ 실행 메소드 ]" + "[===" + joinPoint.getSignature().getName() + "===]" + "\n";
        List<String> args = Arrays.asList(joinPoint.getArgs()).stream()
                .map(String::valueOf).map(e -> e = "[ " + getTime() + " ]" + "[ 매개변수 ] " + e + "\n")
                .collect(Collectors.toList());

        for (int i = 0; i < args.size(); i++) {
            log += args.get(i);
        }

        // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
        BufferedWriter fw = new BufferedWriter(new FileWriter(path + fileName, true));

        // 파일안에 문자열 쓰기
        fw.write(log);
        fw.flush();

        // 객체 닫기
        fw.close();
    }

    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
    private String getTime() { return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
