package com.wanted.preonboarding.domain.recruitment.dto.request;

import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecruitmentPostRequestDto {

    private Long companyId;

    private String position;

    private Integer reward;

    private String description;

    private String skill;

    public static RecruitmentPostRequestDto of(long companyId,
                                               String position,
                                               int reward,
                                               String description,
                                               String skill) {
        return new RecruitmentPostRequestDto(companyId, position, reward, description, skill);
    }

    public Recruitment toEntity() {
        return Recruitment.builder()
                .companyId(companyId)
                .position(position)
                .reward(reward)
                .description(description)
                .skill(skill)
                .build();
    }

}