package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.company.entity.Company;
import com.wanted.preonboarding.domain.recruitment.controller.RecruitmentController;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentSearchCondition;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentVo;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentsGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecruitmentQueryService {
    private final RecruitmentRepository recruitmentRepository;

    public CollectionModel<EntityModel<RecruitmentsGetResponseDto>> getRecruitments(RecruitmentSearchCondition searchCondition) {

        List<EntityModel<RecruitmentsGetResponseDto>> recruitmentResource = recruitmentRepository.findRecruitments(searchCondition).stream()
                .map(
                recruitmentRes -> EntityModel.of(
                        recruitmentRes,
                        linkTo(RecruitmentController.class).slash(recruitmentRes.getId()).withSelfRel(),
                        linkTo(RecruitmentController.class).slash(recruitmentRes.getId()).withRel("update"),
                        linkTo(RecruitmentController.class).slash(recruitmentRes.getId()).withRel("delete"),
                        linkTo(RecruitmentController.class).withRel("queryRecruitments")
                )
        ).toList();

        return CollectionModel.of(recruitmentResource,
                linkTo(RecruitmentController.class).withSelfRel(),
                Link.of("/docs/index.html#resources-recruitments-list").withRel("profile")
        );

    }

    public EntityModel<RecruitmentGetResponseDto> getRecruitment(long id) {
        RecruitmentVo recruitmentVo = recruitmentRepository.findRecruitmentBy(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 채용 공고가 없습니다." + id));

        Recruitment recruitment = recruitmentVo.getRecruitment();
        Company company = recruitmentVo.getCompany();
        List<Long> anotherRecruitments = getCompanyAnotherRecruitments(id, company);

        List<Link> anotherRecruitmentResources = anotherRecruitments.stream().map(
                recruitmentId -> linkTo(RecruitmentController.class).slash(recruitmentId).withRel("queryAnotherRecruitments")
        ).toList();

        Links anotherRecruitmentResourceLinks = Links.of(anotherRecruitmentResources);
        RecruitmentGetResponseDto content = RecruitmentGetResponseDto.of(recruitment, company, anotherRecruitments);

        WebMvcLinkBuilder link = linkTo(RecruitmentController.class).slash(id);

        return EntityModel.of(
                content,
                link.withSelfRel(),
                link.withRel("update"),
                link.withRel("delete"),
                linkTo(RecruitmentController.class).withRel("queryRecruitments"),
                Link.of("/docs/index.html#resources-recruitment-get").withRel("profile")
        ).add(anotherRecruitmentResourceLinks);

    }

    private List<Long> getCompanyAnotherRecruitments(long recruitmentId, Company company) {
        List<Recruitment> recruitments = recruitmentRepository
                .findByCompanyId(company.getId());

        return recruitments.stream()
                .filter(recruitment -> recruitment.getId() != recruitmentId)
                .map(recruitment -> recruitment.getId())
                .toList();
    }

}
