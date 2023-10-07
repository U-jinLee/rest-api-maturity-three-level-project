package com.wanted.preonboarding.domain.recruitment.dto.response;

import com.wanted.preonboarding.domain.recruitment.controller.RecruitmentController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class RecruitmentUpdateAssembler implements
        RepresentationModelAssembler<RecruitmentPutResponseDto, EntityModel<RecruitmentPutResponseDto>> {
    @Override
    public EntityModel<RecruitmentPutResponseDto> toModel(RecruitmentPutResponseDto responseDto) {

        WebMvcLinkBuilder link = linkTo(RecruitmentController.class).slash(responseDto.getId());

        return EntityModel.of(
                responseDto,
                link.withSelfRel(),
                link.withRel("delete"),
                linkTo(RecruitmentController.class).withRel("queryRecruitments"),
                link.withRel("queryRecruitment"),
                Link.of("/docs/index.html#resources-recruitments-update").withRel("profile"));
    }
}
