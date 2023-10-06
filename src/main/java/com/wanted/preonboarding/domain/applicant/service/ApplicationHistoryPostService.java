package com.wanted.preonboarding.domain.applicant.service;

import com.wanted.preonboarding.domain.applicant.controller.ApplicantController;
import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryRequestDto;
import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryResponseDto;
import com.wanted.preonboarding.domain.applicant.entity.Applicant;
import com.wanted.preonboarding.domain.applicant.entity.ApplicationHistory;
import com.wanted.preonboarding.domain.applicant.repository.ApplicantRepository;
import com.wanted.preonboarding.domain.applicant.repository.ApplicationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@Service
public class ApplicationHistoryPostService {

    private final ApplicantRepository applicantRepository;
    private final ApplicationHistoryRepository applicationHistoryRepository;

    @Transactional
    public EntityModel<ApplicationHistoryResponseDto> postApplicationHistory(long applicantId, ApplicationHistoryRequestDto requestDto) {

        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(() ->
                new IllegalArgumentException("해당 지원자가 존재하지 않습니다: " + applicantId));

       if (applicationHistoryRepository.existsByApplicantAndRecruitmentId(applicant, requestDto.getRecruitmentId()))
           throw new IllegalArgumentException("이미 지원한 공고입니다: " + requestDto.getRecruitmentId());

        ApplicationHistory applicationHistory = applicationHistoryRepository.save(
                ApplicationHistory.builder()
                        .applicant(applicant)
                        .recruitmentId(requestDto.getRecruitmentId())
                        .build()
        );

        return EntityModel.of(ApplicationHistoryResponseDto.from(applicationHistory),
                linkTo(ApplicantController.class)
                        .slash(applicantId)
                        .slash("application-histories")
                        .slash(applicationHistory.getId()).withSelfRel(),
                Link.of("/docs/index.html#resources-applicants-id-application-histories-create")
                        .withRel("profile"));
    }
}
