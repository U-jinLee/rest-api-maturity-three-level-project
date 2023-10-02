package com.wanted.preonboarding.domain.recruitment.service;

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
        recruitmentRepository.deleteById(id);
    }

}
