package com.wanted.preonboarding.domain.recruitment.dto.assembler;

import com.wanted.preonboarding.domain.recruitment.controller.RecruitmentController;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPostResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class RecruitmentPostAssembler implements
        RepresentationModelAssembler<RecruitmentPostResponseDto, EntityModel<RecruitmentPostResponseDto>> {
    @Override
    public EntityModel<RecruitmentPostResponseDto> toModel(RecruitmentPostResponseDto responseDto) {

        WebMvcLinkBuilder link = linkTo(RecruitmentController.class).slash(responseDto.getId());

        return EntityModel.of(
                responseDto,
                link.withSelfRel(),
                link.withRel("delete"),
                link.withRel("update"),
                linkTo(RecruitmentController.class).withRel("queryRecruitments"),
                link.withRel("queryRecruitment"),
                Link.of("/docs/index.html#resources-recruitments-create").withRel("profile"));
    }
}
