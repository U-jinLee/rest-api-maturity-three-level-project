package com.wanted.preonboarding.domain.recruitment.service;

import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.exception.RecruitmentNotFoundException;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecruitmentDeleteService {

    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public void delete(long id) {
        Recruitment recruitment = recruitmentRepository.findById(id).orElseThrow(() -> new
                RecruitmentNotFoundException("해당 채용공고를 찾을 수 없습니다: " + id));

        recruitmentRepository.delete(recruitment);
    }

}
