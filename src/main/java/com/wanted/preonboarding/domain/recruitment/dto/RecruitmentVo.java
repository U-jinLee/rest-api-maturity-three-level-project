package com.wanted.preonboarding.domain.recruitment.dto;

import com.wanted.preonboarding.domain.company.entity.Company;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import lombok.Getter;

@Getter
public class RecruitmentVo {

    private Recruitment recruitment;
    private Company company;

    public RecruitmentVo(Recruitment recruitment, Company company) {
        this.recruitment = recruitment;
        this.company = company;
    }

}
