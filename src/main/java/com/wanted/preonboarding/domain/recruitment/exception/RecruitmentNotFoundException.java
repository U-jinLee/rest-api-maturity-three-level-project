package com.wanted.preonboarding.domain.recruitment.exception;

import com.wanted.preonboarding.global.error.exception.EntityNotFoundException;

public class RecruitmentNotFoundException extends EntityNotFoundException {
    public RecruitmentNotFoundException(String message) {
        super(message);
    }

}
