package com.wanted.preonboarding.domain.recruitment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
