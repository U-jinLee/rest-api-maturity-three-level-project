package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.controller.RecruitmentController;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPostResponseDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@Transactional
@Service
public class RecruitmentPostService {
    private final RecruitmentRepository recruitmentRepository;

    public EntityModel<RecruitmentPostResponseDto> post(RecruitmentPostRequestDto recruitmentPostRequestDto) {

        Recruitment recruitment = recruitmentRepository.save(recruitmentPostRequestDto.toEntity());

        return EntityModel.of(RecruitmentPostResponseDto.from(recruitment),
                linkTo(RecruitmentController.class)
                        .slash(recruitment.getId()).withSelfRel(),
                Link.of("/docs/index.html#resources-recruitments-create").withRel("profile")
        );

    }
}
