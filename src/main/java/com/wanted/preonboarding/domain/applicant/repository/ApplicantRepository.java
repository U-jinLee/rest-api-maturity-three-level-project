package com.wanted.preonboarding.domain.applicant.repository;

import com.wanted.preonboarding.domain.applicant.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
