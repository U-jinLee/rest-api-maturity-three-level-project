package com.wanted.preonboarding.domain.recruitment.controller;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPutRequestDto;
import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.global.setup.IntegrationTest;
import com.wanted.preonboarding.global.setup.RecruitmentSetUp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecruitmentControllerTest extends IntegrationTest {

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


}