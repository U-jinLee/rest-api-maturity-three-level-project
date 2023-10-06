package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.controller.RecruitmentController;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPostResponseDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

        WebMvcLinkBuilder link = linkTo(RecruitmentController.class)
                .slash(recruitment.getId());

        return EntityModel.of(RecruitmentPostResponseDto.from(recruitment),
                link.withSelfRel(),
                link.withRel("delete"),
                link.withRel("update"),
                linkTo(RecruitmentController.class).withRel("queryRecruitments"),
                link.withRel("queryRecruitment"),
                Link.of("/docs/index.html#resources-recruitments-create").withRel("profile")
        );

    }
}
