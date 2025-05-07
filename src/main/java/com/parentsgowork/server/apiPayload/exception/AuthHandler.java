package com.parentsgowork.server.apiPayload.exception;

import com.parentsgowork.server.apiPayload.code.BaseErrorCode;

public class AuthHandler extends GeneralException {
    public AuthHandler(BaseErrorCode code) {
        super(code);
    }

}
