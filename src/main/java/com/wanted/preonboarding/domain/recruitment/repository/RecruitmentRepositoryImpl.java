package com.wanted.preonboarding.domain.recruitment.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentSearchCondition;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentVo;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentsGetResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.wanted.preonboarding.domain.company.entity.QCompany.company;
import static com.wanted.preonboarding.domain.recruitment.entity.QRecruitment.recruitment;
import static io.micrometer.common.util.StringUtils.isEmpty;

@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RecruitmentsGetResponseDto> findRecruitments(RecruitmentSearchCondition searchCondition) {

        return jpaQueryFactory.select(
                        Projections.constructor(
                                RecruitmentsGetResponseDto.class,
                                recruitment,
                                company
                        )
                )
                .from(recruitment)
                .innerJoin(company).on(recruitment.companyId.eq(company.id))
                .where(
                        recruitmentSkillEq(searchCondition.getSkill()),
                        companyNameEq(searchCondition.getCompany())
                )
                .fetch();
    }

    private BooleanExpression recruitmentSkillEq(String skill) {
        return isEmpty(skill) ? null : recruitment.skill.eq(skill);
    }

    private BooleanExpression companyNameEq(String companyName) {
        return isEmpty(companyName) ? null : company.name.eq(companyName);
    }

    @Override
    public Optional<RecruitmentVo> findRecruitmentBy(long id) {
        return Optional.of(jpaQueryFactory.select(
                        Projections.constructor(
                                RecruitmentVo.class,
                                recruitment,
                                company
                        )
                )
                .from(recruitment)
                .innerJoin(company).on(recruitment.companyId.eq(company.id))
                .where(recruitment.id.eq(id))
                .fetchOne());
    }
}
