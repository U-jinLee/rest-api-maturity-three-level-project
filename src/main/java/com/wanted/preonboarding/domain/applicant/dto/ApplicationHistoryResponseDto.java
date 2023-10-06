package com.wanted.preonboarding.domain.applicant.dto;

import com.wanted.preonboarding.domain.applicant.entity.ApplicationHistory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationHistoryResponseDto {

    private long applicationHistoryId;
    private long recruitmentId;
    private long applicantId;

    public static ApplicationHistoryResponseDto from(ApplicationHistory applicationHistory) {
        return new ApplicationHistoryResponseDto(
                applicationHistory.getId(),
                applicationHistory.getRecruitmentId(),
                applicationHistory.getApplicantId());
    }

}
