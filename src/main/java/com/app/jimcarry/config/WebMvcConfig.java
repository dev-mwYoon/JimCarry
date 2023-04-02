package com.app.jimcarry.config;

import com.app.jimcarry.intercepter.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 컨트롤러에 요청이 닿기 전에 처리하 인터셉터를 WebMvcConfigurer를 통해
 * 원하는 url에 등록하는 설정 클래스이다.
 * */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/users/mypage/**"); // 적용할 URL 패턴
//                .excludePathPatterns("/login/**"); // 제외할 URL 패턴
    }
}
