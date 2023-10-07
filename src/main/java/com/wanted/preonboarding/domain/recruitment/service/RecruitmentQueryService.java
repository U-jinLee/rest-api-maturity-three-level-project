package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.company.entity.Company;
import com.wanted.preonboarding.domain.recruitment.controller.RecruitmentController;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentSearchCondition;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentVo;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentGet;
import com.wanted.preonboarding.domain.recruitment.dto.assembler.RecruitmentGetAssembler;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.exception.RecruitmentNotFoundException;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecruitmentQueryService {
    private final RecruitmentGetAssembler assembler;
    private final RecruitmentRepository recruitmentRepository;

    public CollectionModel<EntityModel<RecruitmentGet>> getRecruitments(RecruitmentSearchCondition searchCondition) {
        return assembler.toCollectionModel(recruitmentRepository.findRecruitments(searchCondition));
    }

    public EntityModel<RecruitmentGet> getRecruitment(long id) {
        RecruitmentVo recruitmentVo = recruitmentRepository.findRecruitmentBy(id).orElseThrow(() ->
                new RecruitmentNotFoundException("해당하는 채용 공고가 없습니다." + id));

        Recruitment recruitment = recruitmentVo.getRecruitment();
        Company company = recruitmentVo.getCompany();
        List<Long> anotherRecruitments = getCompanyAnotherRecruitments(id, company);

        return assembler.toModel(RecruitmentGetResponseDto.of(recruitment, company, anotherRecruitments))
                .add(getLinksAbout(anotherRecruitments))
                .add(Link.of("/docs/index.html#resources-recruitment-get").withRel("profile"));

    }

    private List<Long> getCompanyAnotherRecruitments(long recruitmentId, Company company) {
        List<Recruitment> recruitments = recruitmentRepository
                .findByCompanyId(company.getId());

        return recruitments.stream()
                .filter(recruitment -> recruitment.getId() != recruitmentId)
                .map(Recruitment::getId)
                .toList();
    }

    private Links getLinksAbout(List<Long> anotherRecruitments) {
        List<Link> anotherRecruitmentResources = anotherRecruitments.stream().map(
                recruitmentId -> linkTo(RecruitmentController.class).slash(recruitmentId).withRel("queryAnotherRecruitments")
        ).toList();

        return Links.of(anotherRecruitmentResources);
    }

}
