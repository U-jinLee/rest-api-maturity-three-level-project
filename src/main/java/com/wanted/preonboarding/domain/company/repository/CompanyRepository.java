package com.wanted.preonboarding.domain.company.repository;

import com.wanted.preonboarding.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}