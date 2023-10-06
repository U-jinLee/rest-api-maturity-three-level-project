package com.wanted.preonboarding.domain.recruitment.controller;

import com.wanted.preonboarding.domain.recruitment.dto.*;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPostRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPutRequestDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPostResponseDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentPutResponseDto;
import com.wanted.preonboarding.domain.recruitment.dto.response.RecruitmentsGetResponseDto;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentDeleteService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentPostService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentQueryService;
import com.wanted.preonboarding.domain.recruitment.service.RecruitmentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
@RestController
public class RecruitmentController {

    private final RecruitmentPostService recruitmentPostService;
    private final RecruitmentUpdateService recruitmentUpdateService;
    private final RecruitmentDeleteService recruitmentDeleteService;
    private final RecruitmentQueryService recruitmentQueryService;

    @PostMapping
    public ResponseEntity<EntityModel<RecruitmentPostResponseDto>> postRecruitment(@RequestBody RecruitmentPostRequestDto requestDto) {
        EntityModel<RecruitmentPostResponseDto> result = recruitmentPostService.post(requestDto);
        return ResponseEntity
                .created(result.getLink(LinkRelation.of("self")).get().toUri())
                .body(result);
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
    public ResponseEntity<List<RecruitmentsGetResponseDto>> getRecruitments(RecruitmentSearchCondition searchCondition) {
        return ResponseEntity.status(HttpStatus.OK).body(recruitmentQueryService.getRecruitments(searchCondition));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruitmentGetResponseDto> getRecruitment(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(recruitmentQueryService.getRecruitment(id));
    }

}
