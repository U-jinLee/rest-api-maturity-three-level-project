package com.wanted.preonboarding.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    ENTITY_NOT_FOUND(404, "C001", " Entity Not Found"),
    INVALID_INPUT_VALUE(400, "C002", " Invalid Input Value"),
    INVALID_TYPE_VALUE(400, "C002", " Invalid Input Value"),

    ALREADY_APPLIED_EXCEPTION(400, "APL001", "This is a job posting that you have already applied for");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
