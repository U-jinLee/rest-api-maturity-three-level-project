package com.wanted.preonboarding.domain.recruitment.dto.response;

import com.wanted.preonboarding.domain.company.entity.Company;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import lombok.Getter;

@Getter
public class RecruitmentsGetResponseDto implements RecruitmentGet {
    private long id;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int reward;
    private String skill;

    public RecruitmentsGetResponseDto(Recruitment recruitment, Company company) {
        this.id = recruitment.getId();
        this.companyName = company.getName();
        this.nation = company.getLocation().getNation();
        this.region = company.getLocation().getRegion();
        this.position = recruitment.getPosition();
        this.reward = recruitment.getReward();
        this.skill = recruitment.getSkill();
    }
}
