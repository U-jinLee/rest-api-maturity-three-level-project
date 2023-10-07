package com.wanted.preonboarding.global.setup;

import com.wanted.preonboarding.domain.applicant.entity.Applicant;
import com.wanted.preonboarding.domain.applicant.entity.ApplicationHistory;
import com.wanted.preonboarding.domain.applicant.repository.ApplicantRepository;
import com.wanted.preonboarding.domain.applicant.repository.ApplicationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class ApplicationHistorySetUp {

    @Autowired
    ApplicationHistoryRepository applicationHistoryRepository;

    public ApplicationHistory save(Applicant applicant, long recruitmentId) {
        return applicationHistoryRepository.save(buildApplicant(applicant, recruitmentId));
    }

    private ApplicationHistory buildApplicant(Applicant applicant, long recruitmentId) {
        return ApplicationHistory.builder()
                .applicant(applicant)
                .recruitmentId(recruitmentId)
                .build();
    }

}
