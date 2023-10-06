package com.wanted.preonboarding.domain.recruitment.dto.request;

import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecruitmentPostRequestDto {

    @NotNull
    private Long companyId;

    @NotEmpty
    private String position;

    @NotNull
    private Integer reward;

    @NotEmpty
    private String description;

    @NotEmpty
    private String skill;

    public static RecruitmentPostRequestDto of(Long companyId,
                                               String position,
                                               Integer reward,
                                               String description,
                                               String skill) {
        return new RecruitmentPostRequestDto(companyId, position, reward, description, skill);
    }

    public Recruitment toEntity() {
        return Recruitment.builder()
                .companyId(companyId)
                .position(position)
                .reward(reward)
                .description(description)
                .skill(skill)
                .build();
    }

}
