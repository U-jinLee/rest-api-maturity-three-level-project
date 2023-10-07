package com.wanted.preonboarding.domain.recruitment.controller;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentSearchCondition;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPutRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.*;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentDeleteService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentPostService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentQueryService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
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
    private final RecruitmentQueryService recruitmentQueryService;

    @PostMapping
    public ResponseEntity<EntityModel<RecruitmentPostResponseDto>> postRecruitment(@RequestBody @Valid RecruitmentPostRequestDto requestDto) {
        EntityModel<RecruitmentPostResponseDto> result = recruitmentPostService.post(requestDto);
        return ResponseEntity
                .created(result.getLink(LinkRelation.of("self")).get().toUri())
                .body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RecruitmentPutResponseDto>> putRecruitment(@PathVariable("id") long id,
                                                                                 @RequestBody RecruitmentPutRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(recruitmentUpdateService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRecruitment(@PathVariable Long id) {
        recruitmentDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RecruitmentGet>>> getRecruitments(RecruitmentSearchCondition searchCondition) {
        return ResponseEntity.status(HttpStatus.OK).body(recruitmentQueryService.getRecruitments(searchCondition));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RecruitmentGet>> getRecruitment(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(recruitmentQueryService.getRecruitment(id));
    }

}
