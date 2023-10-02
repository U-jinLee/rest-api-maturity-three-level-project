package com.wanted.preonboarding.domain.recruitment.controller;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPostResponseDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPutRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPutResponseDto;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentDeleteService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentPostService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
@RestController
public class RecruitmentController {

    private final RecruitmentPostService recruitmentPostService;
    private final RecruitmentUpdateService recruitmentUpdateService;
    private final RecruitmentDeleteService recruitmentDeleteService;

    @PostMapping
    public ResponseEntity<RecruitmentPostResponseDto> postRecruitment(@RequestBody RecruitmentPostRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recruitmentPostService.post(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruitmentPutResponseDto> putRecruitment(@PathVariable("id") long id,
                                                                    @RequestBody RecruitmentPutRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(recruitmentUpdateService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRecruitment(@PathVariable Long id) {
        recruitmentDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
