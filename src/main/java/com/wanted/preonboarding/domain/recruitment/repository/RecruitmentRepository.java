package com.wanted.preonboarding.domain.recruitment.repository;

import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>, RecruitmentRepositoryCustom {
    List<Recruitment> findByCompanyId(long companyId);

}