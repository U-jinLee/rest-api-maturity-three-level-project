package com.wanted.preonboarding.domain.applicant.controller;

import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryRequestDto;
import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryResponseDto;
import com.wanted.preonboarding.domain.applicant.service.ApplicationHistoryPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/applicants")
@RestController
public class ApplicantController {

    private final ApplicationHistoryPostService applicationHistoryPostService;

    @PostMapping("/{applicant-id}/application-histories")
    public ResponseEntity<EntityModel<ApplicationHistoryResponseDto>> postApplicationHistory(@PathVariable(name = "applicant-id") long applicantId,
                                                                                             @RequestBody @Valid ApplicationHistoryRequestDto requestDto) {
        EntityModel<ApplicationHistoryResponseDto> result =
                applicationHistoryPostService.postApplicationHistory(applicantId, requestDto);

        return ResponseEntity.created(result.getLink(LinkRelation.of("self")).get().toUri()).body(result);
    }

}
