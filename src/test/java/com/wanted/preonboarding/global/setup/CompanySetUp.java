package com.wanted.preonboarding.global.setup;

import com.wanted.preonboarding.domain.company.entity.Company;
import com.wanted.preonboarding.domain.company.model.Location;
import com.wanted.preonboarding.domain.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class CompanySetUp {

    @Autowired
    CompanyRepository companyRepository;

    public Company save(String name) {
        return companyRepository.save(buildRecruitment(name));
    }

    private Company buildRecruitment(String name) {
        return Company.builder()
                .name(name)
                .location(Location.of("한국", "서울"))
                .build();
    }

}
