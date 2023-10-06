package com.wanted.preonboarding.domain.applicant.controller;

import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryRequestDto;
import com.wanted.preonboarding.global.config.RestDocsConfiguration;
import com.wanted.preonboarding.global.setup.ApplicantSetUp;
import com.wanted.preonboarding.global.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(RestDocsConfiguration.class)
@AutoConfigureRestDocs
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
                .andDo(document(
                        "post-applicant-application-history",
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-Type header"),
                                headerWithName(HttpHeaders.CONTENT_LENGTH).description("Content-Length header")
                        ),
                        requestFields(
                                fieldWithPath("recruitmentId").description("지원할 채용공고의 id")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("생성된 지원이력의 id"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-Type header")
                        ),
                        responseFields(
                                fieldWithPath("applicationHistoryId").description("생성된 지원이력의 id"),
                                fieldWithPath("recruitmentId").description("지원한 채용공고의 id"),
                                fieldWithPath("applicantId").description("지원자의 id"),
                                fieldWithPath("_links.*").ignored(),
                                fieldWithPath("_links.self.*").ignored(),
                                fieldWithPath("_links.profile.*").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self"),
                                linkWithRel("profile").description("Link to profile")
                        )
                ))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("recruitmentId").value(request.getRecruitmentId()))
                .andExpect(jsonPath("applicantId").value(applicantId))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.profile").exists());
    }

    private ResultActions requestApplicationHistory(long applicantId, ApplicationHistoryRequestDto request) throws Exception {
        return mvc.perform(post("/api/applicants/{id}/application-histories", applicantId)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));
    }

}