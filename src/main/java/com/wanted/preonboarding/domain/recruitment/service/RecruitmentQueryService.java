package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentsGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecruitmentQueryService {
    private final RecruitmentRepository recruitmentRepository;

    public List<RecruitmentsGetResponseDto> getRecruitments() {
        return recruitmentRepository.findRecruitments();
    }
}
