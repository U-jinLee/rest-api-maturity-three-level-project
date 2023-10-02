package com.wanted.preonboarding.domain.recruitment.dto;

import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentPutResponseDto {

    private long id;
    private long companyId;
    private String position;
    private int reward;
    private String description;
    private String skill;

    public static RecruitmentPutResponseDto from(Recruitment recruitment) {
        return new RecruitmentPutResponseDto(recruitment.getId(),
                recruitment.getCompanyId(),
                recruitment.getPosition(),
                recruitment.getReward(),
                recruitment.getDescription(),
                recruitment.getSkill());
    }

}
