package com.wanted.preonboarding.domain.applicant.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantTest {

    @Test
    void 지원자_정보_가져오기() {
        Applicant applicant = Applicant.builder()
                .name("이유진")
                .build();

        assertEquals(applicant.toString(),
                "Applicant(id=null, name=이유진)"
                );

    }
}