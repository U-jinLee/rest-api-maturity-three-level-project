package com.wanted.preonboarding.domain.applicant.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationHistoryRequestDto {

    private Long recruitmentId;

    public static ApplicationHistoryRequestDto of(long recruitmentId) {
        return new ApplicationHistoryRequestDto(recruitmentId);
    }

}
