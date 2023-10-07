package com.wanted.preonboarding.domain.applicant.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationHistoryRequestDto {

    @NotNull
    private Long recruitmentId;

    public static ApplicationHistoryRequestDto from(Long recruitmentId) {
        return new ApplicationHistoryRequestDto(recruitmentId);
    }

}
