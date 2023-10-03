package com.wanted.preonboarding.domain.company.entity;

import com.wanted.preonboarding.domain.company.model.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyTest {
    @Test
    public void 회사_정보_가져오기() {

        final Company company = Company.builder()
                .name("테스트")
                .location(Location.of("서울시", "강남구"))
                .build();

        assertEquals(company.toString(), "Company(id=null, name=테스트, location=Location(nation=서울시, region=강남구))"
        );
    }
}