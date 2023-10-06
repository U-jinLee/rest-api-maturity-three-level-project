package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPutRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPutResponseDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RecruitmentUpdateService {
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public RecruitmentPutResponseDto update(long id, RecruitmentPutRequestDto requestDto) {
        Recruitment recruitment = recruitmentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        recruitment.update(requestDto);

        return RecruitmentPutResponseDto.from(recruitment);
    }
}
