package com.wanted.preonboarding.domain.company.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Location {
    private String nation;
    private String region;

    public static Location of(String nation, String region) {
        return new Location(nation, region);
    }
}
