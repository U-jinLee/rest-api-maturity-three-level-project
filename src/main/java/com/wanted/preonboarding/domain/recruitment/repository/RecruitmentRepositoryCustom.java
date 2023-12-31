package com.wanted.preonboarding.domain.recruitment.repository;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentSearchCondition;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentVo;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentsGetResponseDto;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepositoryCustom {
    List<RecruitmentsGetResponseDto> findRecruitments(RecruitmentSearchCondition searchCondition);
    Optional<RecruitmentVo> findRecruitmentBy(long id);
}
