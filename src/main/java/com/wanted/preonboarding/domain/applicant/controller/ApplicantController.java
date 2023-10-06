package com.wanted.preonboarding.domain.applicant.controller;

import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryRequestDto;
import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryResponseDto;
import com.wanted.preonboarding.domain.applicant.service.ApplicationHistoryPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RequestMapping("/api/applicants")
@RestController
public class ApplicantController {

    private final ApplicationHistoryPostService applicationHistoryPostService;

    @PostMapping("/{id}/application-histories")
    public ResponseEntity<ApplicationHistoryResponseDto> postApplicationHistory(@PathVariable(name = "id") long id,
                                                                                @RequestBody ApplicationHistoryRequestDto requestDto) {
        ApplicationHistoryResponseDto result = applicationHistoryPostService.postApplicationHistory(id, requestDto);
        return ResponseEntity
                .created(linkTo(methodOn(ApplicantController.class).postApplicationHistory(id, requestDto)).slash(result.getApplicationHistoryId()).toUri())
                .body(result);

    }

}
