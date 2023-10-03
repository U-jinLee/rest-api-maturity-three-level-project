package com.wanted.preonboarding.domain.recruitment.repository;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentsGetResponseDto;

import java.util.List;

public interface RecruitmentRepositoryCustom {
    List<RecruitmentsGetResponseDto> findRecruitments();
}
