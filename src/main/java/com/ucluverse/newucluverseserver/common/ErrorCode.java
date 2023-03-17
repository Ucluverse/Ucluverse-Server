package com.ucluverse.newucluverseserver.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ;
    private final String message;
    private final int status;
}
