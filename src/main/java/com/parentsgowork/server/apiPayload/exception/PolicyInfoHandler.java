package com.parentsgowork.server.apiPayload.exception;

import com.parentsgowork.server.apiPayload.code.BaseErrorCode;

public class PolicyInfoHandler extends GeneralException {
    public PolicyInfoHandler(BaseErrorCode code) {
        super(code);
    }
}
