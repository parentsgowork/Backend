package com.parentsgowork.server.apiPayload.exception;

import com.parentsgowork.server.apiPayload.code.BaseErrorCode;

public class JobInfoCrawlingHandler extends GeneralException {
    public JobInfoCrawlingHandler(BaseErrorCode code) {
        super(code);
    }
}
