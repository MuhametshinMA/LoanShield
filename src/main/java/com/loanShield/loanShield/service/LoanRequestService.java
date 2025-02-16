package com.loanShield.loanShield.service;

import com.loanShield.loanShield.domain.RequestContent;
import com.loanShield.loanShield.dto.LoanRequestDTO;

public interface LoanRequestService {

    void saveLoanRequest(LoanRequestDTO loanRequestDTO, RequestContent requestContent);
}
