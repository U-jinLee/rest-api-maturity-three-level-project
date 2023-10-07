package com.wanted.preonboarding.domain.applicant.exception;

import com.wanted.preonboarding.global.error.exception.EntityNotFoundException;

public class ApplicantNotFoundException extends EntityNotFoundException {

    public ApplicantNotFoundException(String message) {
        super(message);
    }
}
