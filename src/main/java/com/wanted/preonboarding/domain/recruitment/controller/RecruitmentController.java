package com.wanted.preonboarding.domain.recruitment.controller;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPostResponseDto;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
@RestController
public class RecruitmentController {

    private final RecruitmentPostService recruitmentPostService;

    @PostMapping
    public ResponseEntity<RecruitmentPostResponseDto> postRecruitment(@RequestBody RecruitmentPostRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recruitmentPostService.post(requestDto));
    }

}
