package com.wanted.preonboarding.domain.recruitment.controller;

import com.wanted.preonboarding.domain.company.entity.Company;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPutRequestDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.global.IntegrationTest;
import com.wanted.preonboarding.global.config.RestDocsConfiguration;
import com.wanted.preonboarding.global.setup.CompanySetUp;
import com.wanted.preonboarding.global.setup.RecruitmentSetUp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(RestDocsConfiguration.class)
@AutoConfigureRestDocs
class RecruitmentControllerTest extends IntegrationTest {

    @Autowired
    CompanySetUp companySetUp;
    @Autowired
    RecruitmentSetUp recruitmentSetUp;

    @Test
    void 채용공고_등록_성공() throws Exception {

        //given
        Long companyId = 0L;
        String position = "백엔드 주니어 개발자";
        Integer reward = 1000000;
        String description = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..";
        String skill = "Python";

        RecruitmentPostRequestDto request =
                RecruitmentPostRequestDto.of(companyId, position, reward, description, skill);

        //when
        ResultActions resultActions = requestRecruitmentPost(request);

        //then
        resultActions
                .andDo(print())
                .andDo(
                        document("post-recruitment",
                                requestFields(
                                        fieldWithPath("companyId").description("회사 ID"),
                                        fieldWithPath("position").description("채용포지션"),
                                        fieldWithPath("reward").description("채용보상금"),
                                        fieldWithPath("description").description("채용내용"),
                                        fieldWithPath("skill").description("사용기술")
                                ),
                                responseHeaders(
                                        headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("채용공고 ID"),
                                        fieldWithPath("companyId").description("회사 ID"),
                                        fieldWithPath("position").description("채용포지션"),
                                        fieldWithPath("reward").description("채용보상금"),
                                        fieldWithPath("description").description("채용내용"),
                                        fieldWithPath("skill").description("사용기술")
                                )
                ))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("companyId").value(companyId))
                .andExpect(jsonPath("position").value(position))
                .andExpect(jsonPath("reward").value(reward))
                .andExpect(jsonPath("description").value(description))
                .andExpect(jsonPath("skill").value(skill));
    }

    private ResultActions requestRecruitmentPost(RecruitmentPostRequestDto request) throws Exception {
        return mvc.perform(post("/api/recruitments")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void 채용공고_변경_성공() throws Exception {
        //given
        final Recruitment setup = recruitmentSetUp.save();

        String position = "백엔드 주니어 개발자";
        Integer reward = 1500000;
        String description = "원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..";
        String skill = "Python";

        RecruitmentPutRequestDto request = RecruitmentPutRequestDto.of(position, reward, description, skill);

        //when
        ResultActions resultActions = requestRecruitmentPut(setup, request);

        //then
        resultActions
                .andDo(print())
                .andDo(
                        document("put-recruitment",
                                requestFields(
                                        fieldWithPath("position").description("채용포지션"),
                                        fieldWithPath("reward").description("채용보상금"),
                                        fieldWithPath("description").description("채용내용"),
                                        fieldWithPath("skill").description("사용기술")
                                ),
                                responseHeaders(
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("채용공고 ID"),
                                        fieldWithPath("companyId").description("회사 ID"),
                                        fieldWithPath("position").description("채용포지션"),
                                        fieldWithPath("reward").description("채용보상금"),
                                        fieldWithPath("description").description("채용내용"),
                                        fieldWithPath("skill").description("사용기술")
                                )
                        ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("reward").value(reward))
                .andExpect(jsonPath("description").value(description));

    }

    private ResultActions requestRecruitmentPut(Recruitment recruitment, RecruitmentPutRequestDto request) throws Exception {
        return mvc.perform(put("/api/recruitments/{id}", recruitment.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    @Test
    void 채용공고_삭제_성공() throws Exception {
        //given
        final Recruitment setup = recruitmentSetUp.save();
        //when
        ResultActions resultActions = requestRecruitmentDelete(setup);
        //then
        resultActions
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private ResultActions requestRecruitmentDelete(Recruitment recruitment) throws Exception {
        return mvc.perform(delete("/api/recruitments/{id}", recruitment.getId()));
    }

    @Test
    void 채용공고_불러오기_성공() throws Exception {
        //given
        Recruitment saveData = recruitmentSetUp.save();
        recruitmentSetUp.save();

        //when
        ResultActions resultActions = requestRecruitmentGets();
        //then
        resultActions
                .andDo(print())
                .andDo(document(
                        "get-recruitments",
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("채용공고 ID"),
                                fieldWithPath("[].companyName").description("회사명"),
                                fieldWithPath("[].nation").description("국가"),
                                fieldWithPath("[].region").description("지역"),
                                fieldWithPath("[].position").description("채용포지션"),
                                fieldWithPath("[].reward").description("채용보상금"),
                                fieldWithPath("[].skill").description("사용기술")
                        )
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(saveData.getId()))
                .andExpect(jsonPath("$[0].companyName").value("원티드랩"))
                .andExpect(jsonPath("$[0].nation").value("한국"))
                .andExpect(jsonPath("$[0].region").value("서울"))
                .andExpect(jsonPath("$[0].position").value(saveData.getPosition()))
                .andExpect(jsonPath("$[0].reward").value(saveData.getReward()))
                .andExpect(jsonPath("$[0].skill").value(saveData.getSkill()));
    }

    private ResultActions requestRecruitmentGets() throws Exception {
        return mvc.perform(
                get("/api/recruitments")
        );
    }

    @Test
    void 채용공고_기술_검색_성공() throws Exception {
        //given
        Recruitment saveData = recruitmentSetUp.save();
        recruitmentSetUp.save();

        //when
        ResultActions resultActions = requestRecruitmentGets("skill", "Java");
        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
    @Test
    void 채용공고_회사_검색_성공() throws Exception {
        //given
        Recruitment saveData = recruitmentSetUp.save();
        recruitmentSetUp.save();

        //when
        ResultActions resultActions = requestRecruitmentGets("company", "잡플래닛");
        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }


    private ResultActions requestRecruitmentGets(String parameterName, String value) throws Exception {
        return mvc.perform(
                get("/api/recruitments")
                        .param(parameterName, value)
        );
    }

    @Test
    void 채용공고_디테일_불러오기_성공() throws Exception {
        //given
        Company company = companySetUp.save("원티드랩");
        Recruitment saveData = recruitmentSetUp.save(company);
        recruitmentSetUp.save(company);
        recruitmentSetUp.save(company);

        //when
        ResultActions resultActions = requestRecruitmentGet(saveData.getId());
        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(saveData.getId()))
                .andExpect(jsonPath("companyName").value("원티드랩"))
                .andExpect(jsonPath("nation").value("한국"))
                .andExpect(jsonPath("region").value("서울"))
                .andExpect(jsonPath("position").value(saveData.getPosition()))
                .andExpect(jsonPath("reward").value(saveData.getReward()))
                .andExpect(jsonPath("skill").value(saveData.getSkill()))
                .andExpect(jsonPath("description").value(saveData.getDescription()))
                .andExpect(jsonPath("anotherRecruitments").isArray())
                .andExpect(jsonPath("anotherRecruitments").isNotEmpty());
    }

    private ResultActions requestRecruitmentGet(long recruitmentId) throws Exception {
        return mvc.perform(
                get("/api/recruitments/{id}", recruitmentId)
        );
    }

}