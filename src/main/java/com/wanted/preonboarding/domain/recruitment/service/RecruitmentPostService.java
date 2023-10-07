package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPostAssembler;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPostResponseDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RecruitmentPostService {
    private final RecruitmentPostAssembler assembler;
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public EntityModel<RecruitmentPostResponseDto> post(RecruitmentPostRequestDto recruitmentPostRequestDto) {

        Recruitment recruitment = recruitmentRepository.save(recruitmentPostRequestDto.toEntity());

        return assembler.toModel(RecruitmentPostResponseDto.from(recruitment));
    }
}
