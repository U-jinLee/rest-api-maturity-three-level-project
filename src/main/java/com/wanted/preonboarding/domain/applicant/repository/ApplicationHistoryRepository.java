package com.wanted.preonboarding.domain.applicant.repository;

import com.wanted.preonboarding.domain.applicant.entity.Applicant;
import com.wanted.preonboarding.domain.applicant.entity.ApplicationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationHistoryRepository extends JpaRepository<ApplicationHistory, Long> {

    boolean existsByApplicantAndRecruitmentId(Applicant applicant, long recruitmentId);

}
