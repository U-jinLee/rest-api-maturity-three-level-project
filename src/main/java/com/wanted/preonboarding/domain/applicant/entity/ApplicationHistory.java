package com.wanted.preonboarding.domain.applicant.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApplicationHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    private Long recruitmentId;

    @Builder
    public ApplicationHistory(Applicant applicant, long recruitmentId) {
        this.applicant = applicant;
        this.recruitmentId = recruitmentId;
    }

    public long getApplicantId() {
        return applicant.getId();
    }

}