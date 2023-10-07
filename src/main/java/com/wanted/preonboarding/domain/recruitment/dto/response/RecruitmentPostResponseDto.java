package com.wanted.preonboarding.domain.recruitment.dto.response;

import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentPostResponseDto {

    private long id;

    private long companyId;

    private String position;

    private int reward;

    private String description;

    private String skill;

    public static RecruitmentPostResponseDto from(Recruitment recruitment) {
        return new RecruitmentPostResponseDto(
                recruitment.getId(),
                recruitment.getCompanyId(),
                recruitment.getPosition(),
                recruitment.getReward(),
                recruitment.getDescription(),
                recruitment.getSkill());
    }

}
