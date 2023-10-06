package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.controller.RecruitmentController;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPutRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPutResponseDto;
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
@Service
public class RecruitmentUpdateService {
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public EntityModel<RecruitmentPutResponseDto> update(long id, RecruitmentPutRequestDto requestDto) {
        Recruitment recruitment = recruitmentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        recruitment.update(requestDto);

        WebMvcLinkBuilder link = linkTo(RecruitmentController.class)
                .slash(recruitment.getId());

        return EntityModel.of(
                RecruitmentPutResponseDto.from(recruitment),
                link.withSelfRel(),
                link.withRel("delete"),
                linkTo(RecruitmentController.class).withRel("queryRecruitments"),
                link.withRel("queryRecruitment"),
                Link.of("/docs/index.html#resources-recruitments-update").withRel("profile")
        );
    }
}
