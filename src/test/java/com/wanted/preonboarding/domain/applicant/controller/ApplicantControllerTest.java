package com.wanted.preonboarding.domain.applicant.controller;

import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryRequestDto;
import com.wanted.preonboarding.global.setup.ApplicantSetUp;
import com.wanted.preonboarding.global.setup.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApplicantControllerTest extends IntegrationTest {
    @Autowired
    ApplicantSetUp applicantSetUp;

    @Test
    void 채용공고_지원_성공() throws Exception {
        //given
        Long applicantId = applicantSetUp.save().getId();
        ApplicationHistoryRequestDto request = ApplicationHistoryRequestDto.of(0);

        //when
        ResultActions resultActions = requestApplicationHistory(applicantId, request);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("recruitmentId").value(request.getRecruitmentId()))
                .andExpect(jsonPath("applicantId").value(applicantId));
    }

    private ResultActions requestApplicationHistory(long applicantId, ApplicationHistoryRequestDto request) throws Exception {
        return mvc.perform(post("/api/applicants/{id}/application-histories", applicantId)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));
    }

}