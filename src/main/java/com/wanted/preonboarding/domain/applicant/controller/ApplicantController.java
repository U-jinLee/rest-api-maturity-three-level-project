package com.wanted.preonboarding.domain.applicant.controller;

import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryRequestDto;
import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryResponseDto;
import com.wanted.preonboarding.domain.applicant.service.ApplicationHistoryPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/applicants")
@RestController
public class ApplicantController {

    private final ApplicationHistoryPostService applicationHistoryPostService;

    @PostMapping("/{id}/application-histories")
    public ResponseEntity<ApplicationHistoryResponseDto> postApplicationHistory(@PathVariable(name = "id") long id,
                                                                                @RequestBody ApplicationHistoryRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationHistoryPostService.postApplicationHistory(id, requestDto));
    }

}
