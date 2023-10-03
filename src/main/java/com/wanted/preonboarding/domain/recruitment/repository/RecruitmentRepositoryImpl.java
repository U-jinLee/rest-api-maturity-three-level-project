package com.wanted.preonboarding.domain.recruitment.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentsGetResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.wanted.preonboarding.domain.company.entity.QCompany.company;
import static com.wanted.preonboarding.domain.recruitment.entity.QRecruitment.recruitment;

@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RecruitmentsGetResponseDto> findRecruitments() {
        return jpaQueryFactory.select(
                        Projections.constructor(
                                RecruitmentsGetResponseDto.class,
                                recruitment,
                                company
                        )
                )
                .from(recruitment)
                .innerJoin(company).on(recruitment.companyId.eq(company.id))
                .fetch();
    }
}
