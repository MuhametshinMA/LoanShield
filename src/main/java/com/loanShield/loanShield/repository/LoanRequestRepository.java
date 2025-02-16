package com.loanShield.loanShield.repository;

import com.loanShield.loanShield.domain.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
}
