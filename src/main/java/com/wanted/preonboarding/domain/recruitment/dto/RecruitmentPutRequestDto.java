package com.wanted.preonboarding.domain.recruitment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentPutRequestDto {

    private String position;

    private Integer reward;

    private String description;

    private String skill;

    public static RecruitmentPutRequestDto of(String position,
                                              int reward,
                                              String description,
                                              String skill) {
        return new RecruitmentPutRequestDto(position, reward, description, skill);
    }

}
