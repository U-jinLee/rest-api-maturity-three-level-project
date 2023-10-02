package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPostResponseDto;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RecruitmentPostService {
    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentPostResponseDto post(RecruitmentPostRequestDto recruitmentPostRequestDto) {
        return RecruitmentPostResponseDto.from(recruitmentRepository.save(recruitmentPostRequestDto.toEntity()));
    }
}
