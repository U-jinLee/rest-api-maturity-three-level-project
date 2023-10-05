package com.wanted.preonboarding.domain.recruitment.dto;

import com.wanted.preonboarding.domain.company.entity.Company;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentGetResponseDto {
    private long id;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int reward;
    private String skill;
    private String description;
    private List<Long> anotherRecruitments = new ArrayList<>();

    public RecruitmentGetResponseDto(Recruitment recruitment, Company company, List<Long> anotherRecruitments) {
        this.id = recruitment.getId();
        this.companyName = company.getName();
        this.nation = company.getLocation().getNation();
        this.region = company.getLocation().getRegion();
        this.position = recruitment.getPosition();
        this.reward = recruitment.getReward();
        this.skill = recruitment.getSkill();
        this.description = recruitment.getDescription();
        this.anotherRecruitments = anotherRecruitments;
    }

    public static RecruitmentGetResponseDto of(Recruitment recruitment, Company company, List<Long> anotherRecruitments) {
        return new RecruitmentGetResponseDto(recruitment, company, anotherRecruitments);
    }

}
