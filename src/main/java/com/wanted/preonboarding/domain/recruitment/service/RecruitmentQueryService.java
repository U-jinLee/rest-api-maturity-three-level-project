package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.company.entity.Company;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentSearchCondition;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentVo;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentsGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecruitmentQueryService {
    private final RecruitmentRepository recruitmentRepository;

    public List<RecruitmentsGetResponseDto> getRecruitments(RecruitmentSearchCondition searchCondition) {
        return recruitmentRepository.findRecruitments(searchCondition);
    }

    public RecruitmentGetResponseDto getRecruitment(long id) {
        RecruitmentVo recruitmentVo = recruitmentRepository.findRecruitmentBy(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 채용 공고가 없습니다." + id));

        Recruitment recruitment = recruitmentVo.getRecruitment();
        Company company = recruitmentVo.getCompany();
        List<Long> anotherRecruitments = getAnotherRecruitments(id, company);

        return RecruitmentGetResponseDto.of(recruitment, company, anotherRecruitments);
    }

    private List<Long> getAnotherRecruitments(long recruitmentId, Company company) {
        List<Recruitment> recruitments = recruitmentRepository
                .findByCompanyId(company.getId());

        return recruitments.stream()
                .filter(recruitment -> recruitment.getId() != recruitmentId)
                .map(recruitment -> recruitment.getId())
                .toList();
    }

}
