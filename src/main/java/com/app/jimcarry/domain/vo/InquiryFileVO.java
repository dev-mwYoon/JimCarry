package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryFileVO extends FileVO{
    private Long inquiryId;
}
