package com.wanted.preonboarding.domain.recruitment.repository;

import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}