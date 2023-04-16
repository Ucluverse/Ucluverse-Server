package com.ucluverse.newucluverseserver.common;

import org.springframework.web.server.ResponseStatusException;

public class CustomResponseStatusException extends ResponseStatusException {

    public CustomResponseStatusException(ErrorCode errorCode) {
        super(errorCode.getHttpStatus(), errorCode.getDetail());
    }
}
