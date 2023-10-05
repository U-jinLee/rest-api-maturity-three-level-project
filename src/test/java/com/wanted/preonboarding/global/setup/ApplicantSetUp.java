package com.wanted.preonboarding.global.setup;

import com.wanted.preonboarding.domain.applicant.entity.Applicant;
import com.wanted.preonboarding.domain.applicant.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class ApplicantSetUp {

    @Autowired
    ApplicantRepository applicantRepository;

    public Applicant save() {
        return applicantRepository.save(buildApplicant());
    }

    private Applicant buildApplicant() {
        return Applicant.builder()
                .name("이지원")
                .build();
    }

}
