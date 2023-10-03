package com.wanted.preonboarding.domain.company.entity;

import com.wanted.preonboarding.domain.company.model.Location;
import jakarta.persistence.*;
import lombok.*;


@ToString
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "company")
@Entity
public class Company {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Location location;

    @Builder
    public Company(String name, Location location) {
        this.name = name;
        this.location = location;
    }

}