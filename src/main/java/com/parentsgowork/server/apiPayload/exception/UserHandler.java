package com.parentsgowork.server.apiPayload.exception;

import com.parentsgowork.server.apiPayload.code.BaseErrorCode;

public class UserHandler extends GeneralException {

    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
