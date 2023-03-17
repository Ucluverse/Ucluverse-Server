package com.ucluverse.newucluverseserver.common.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseResponse <T> {
    private String status;
    private String message;
    private T data;
}
