package com.wanted.preonboarding.domain.applicant.exception;

import com.wanted.preonboarding.global.error.exception.BusinessException;
import com.wanted.preonboarding.global.error.exception.ErrorCode;

public class AlreadyAppliedException extends BusinessException {
    public AlreadyAppliedException() {
        super(ErrorCode.ALREADY_APPLIED_EXCEPTION);
    }
}
