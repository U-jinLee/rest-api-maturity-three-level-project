package com.wanted.preonboarding.domain.recruitment.dto.assembler;

import com.wanted.preonboarding.domain.recruitment.controller.RecruitmentController;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentGet;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class RecruitmentGetAssembler implements
        RepresentationModelAssembler<RecruitmentGet, EntityModel<RecruitmentGet>> {
    @Override
    public EntityModel<RecruitmentGet> toModel(RecruitmentGet responseDto) {

        WebMvcLinkBuilder link = linkTo(RecruitmentController.class).slash(responseDto.getId());

        return EntityModel.of(
                responseDto,
                link.withSelfRel(),
                link.withRel("update"),
                link.withRel("delete"),
                linkTo(RecruitmentController.class).withRel("queryRecruitments")
        );
    }

    @Override
    public CollectionModel<EntityModel<RecruitmentGet>> toCollectionModel(Iterable<? extends RecruitmentGet> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(RecruitmentController.class).withSelfRel())
                .add(Link.of("/docs/index.html#resources-recruitments-list").withRel("profile"));
    }
}
