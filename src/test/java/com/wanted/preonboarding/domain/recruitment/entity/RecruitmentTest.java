package com.wanted.preonboarding.domain.recruitment.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecruitmentTest {
    @Test
    public void 채용_정보_가져오기() {
        final Recruitment recruitment = Recruitment.builder()
                .companyId(1L)
                .position("Backend Developer")
                .reward(3000000)
                .description("Backend Developer를 모집합니다.")
                .skill("Java")
                .build();
        assertEquals(recruitment.toString(),
                "Recruitment(id=null, companyId=1, position=Backend Developer, reward=3000000, " +
                        "description=Backend Developer를 모집합니다., skill=Java)"
                );
    }
}