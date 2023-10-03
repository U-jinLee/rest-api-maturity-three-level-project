package com.wanted.preonboarding.domain.recruitment.controller;

import com.wanted.preonboarding.domain.recruitment.dto.*;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentDeleteService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentPostService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentQueryService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
@RestController
public class RecruitmentController {

    private final RecruitmentPostService recruitmentPostService;
    private final RecruitmentUpdateService recruitmentUpdateService;
    private final RecruitmentDeleteService recruitmentDeleteService;
    private final RecruitmentQueryService recruitmentQueryService;

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

    @GetMapping
    public ResponseEntity<List<RecruitmentsGetResponseDto>> getRecruitments() {
        return ResponseEntity.status(HttpStatus.OK).body(recruitmentQueryService.getRecruitments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecruitmentGetResponseDto> getRecruitment(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(recruitmentQueryService.getRecruitment(id));
    }

}
