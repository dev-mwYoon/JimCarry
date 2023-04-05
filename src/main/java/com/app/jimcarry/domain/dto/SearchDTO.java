package com.app.jimcarry.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SearchDTO {
    //    검색조건 타입 설정
    private List<String> types;
    private final boolean IS_SEARCH_DTO = true;
    private boolean isDesc;
    //    검색조건 필드(조건 추가시 필드 추가)
    private String keyword;
    private Long userId;
    private Long storageId;
    private String storageAddress;
    private Integer storageAddressNumber;
    private String storageSize;
    private String storageAddressDetail;
    private String userName;
    private String userIdentification;
    private String userAddress;
    private String noticeWriter;
    private String noticeTitle;
    private String reviewContext;
    private String reviewTitle;
    private String inquiryContent;
    private String inquiryTitle;
    private List<String> sizes; // 특대, 대, 중, 소
    private String order;

    public SearchDTO createTypes(List<String> types){
        setTypes(types);
        return this;
    }
}
