package com.wanted.preonboarding.domain.applicant.dto;

import com.wanted.preonboarding.domain.applicant.controller.ApplicantController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class ApplicationHistoryAssembler implements
        RepresentationModelAssembler<ApplicationHistoryResponseDto, EntityModel<ApplicationHistoryResponseDto>> {
    @Override
    public EntityModel<ApplicationHistoryResponseDto> toModel(ApplicationHistoryResponseDto responseDto) {
        return EntityModel.of(responseDto,
                linkTo(ApplicantController.class)
                        .slash(responseDto.getApplicantId())
                        .slash("application-histories")
                        .slash(responseDto.getApplicationHistoryId()).withSelfRel(),
                Link.of("/docs/index.html#resources-applicants-id-application-histories-create")
                        .withRel("profile"));
    }
}
