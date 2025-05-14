package com.parentsgowork.server.apiPayload.exception;

import com.parentsgowork.server.apiPayload.code.BaseErrorCode;

public class JobInfoHandler extends GeneralException {
    public JobInfoHandler(BaseErrorCode code) {
        super(code);
    }
}
