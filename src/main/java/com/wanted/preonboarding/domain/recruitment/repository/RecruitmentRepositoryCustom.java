package com.wanted.preonboarding.domain.recruitment.repository;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentsGetResponseDto;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepositoryCustom {
    List<RecruitmentsGetResponseDto> findRecruitments();
    Optional<RecruitmentGetResponseDto> findRecruitmentBy(long id);
}
