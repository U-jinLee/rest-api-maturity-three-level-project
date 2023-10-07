package com.wanted.preonboarding.domain.applicant.service;

import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryAssembler;
import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryRequestDto;
import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryResponseDto;
import com.wanted.preonboarding.domain.applicant.entity.Applicant;
import com.wanted.preonboarding.domain.applicant.exception.AlreadyAppliedException;
import com.wanted.preonboarding.domain.applicant.exception.ApplicantNotFoundException;
import com.wanted.preonboarding.domain.applicant.repository.ApplicantRepository;
import com.wanted.preonboarding.domain.applicant.repository.ApplicationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApplicationHistoryPostService {

    private final ApplicationHistoryAssembler assembler;
    private final ApplicantRepository applicantRepository;
    private final ApplicationHistoryRepository applicationHistoryRepository;

    @Transactional
    public EntityModel<ApplicationHistoryResponseDto> postApplicationHistory(long applicantId,
                                                                             ApplicationHistoryRequestDto requestDto) {

        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(() ->
                new ApplicantNotFoundException("해당 지원자가 존재하지 않습니다: " + applicantId));

        validateApplicationHistoryAlreadyExist(applicant, requestDto.getRecruitmentId());

        return assembler.toModel(ApplicationHistoryResponseDto
                .from(applicationHistoryRepository.save(requestDto.toEntity(applicant))));

    }

    private void validateApplicationHistoryAlreadyExist(Applicant applicant, long recruitmentId) {
        if (applicationHistoryRepository.existsByApplicantAndRecruitmentId(applicant, recruitmentId))
            throw new AlreadyAppliedException();
    }

}
