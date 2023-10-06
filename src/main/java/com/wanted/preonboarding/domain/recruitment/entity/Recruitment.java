package com.wanted.preonboarding.domain.recruitment.entity;

import com.wanted.preonboarding.domain.recruitment.dto.request.RecruitmentPutRequestDto;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "recruitment")
@Entity
public class Recruitment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "reward")
    private Integer reward;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "skill", nullable = false)
    private String skill;

    @Builder
    public Recruitment(long companyId, String position, int reward, String description, String skill) {
        this.companyId = companyId;
        this.position = position;
        this.reward = reward;
        this.description = description;
        this.skill = skill;
    }

    public void update(RecruitmentPutRequestDto requestDto) {
        this.position = requestDto.getPosition();
        this.reward = requestDto.getReward();
        this.description = requestDto.getDescription();
        this.skill = requestDto.getSkill();
    }

}