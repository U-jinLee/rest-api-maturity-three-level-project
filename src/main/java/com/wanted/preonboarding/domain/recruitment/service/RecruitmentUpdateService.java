package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPutRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPutResponseDto;
import com.wanted.preonboarding.domain.recruitment.dto.assembler.RecruitmentUpdateAssembler;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.exception.RecruitmentNotFoundException;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RecruitmentUpdateService {
    private final RecruitmentUpdateAssembler assembler;
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public EntityModel<RecruitmentPutResponseDto> update(long id, RecruitmentPutRequestDto requestDto) {

        Recruitment recruitment = recruitmentRepository.findById(id).orElseThrow(() ->
                new RecruitmentNotFoundException("해당 채용공고를 찾을 수 없습니다: " + id));

        recruitment.update(requestDto);

        return assembler.toModel(RecruitmentPutResponseDto.from(recruitment));
    }
}
