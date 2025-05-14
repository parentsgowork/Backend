package com.parentsgowork.server.apiPayload.exception;

import com.parentsgowork.server.apiPayload.code.BaseErrorCode;

public class EducationInfoHandler extends GeneralException {
    public EducationInfoHandler(BaseErrorCode code) {
        super(code);
    }
}
