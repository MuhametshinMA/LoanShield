package com.loanShield.loanShield.mapper;

import com.loanShield.loanShield.domain.LoanRequest;
import com.loanShield.loanShield.dto.LoanRequestDTO;
import com.loanShield.loanShield.dto.RegPersonDTO;
import org.springframework.stereotype.Component;

@Component
public class LoanRequestMapper {

    public LoanRequest toEntity(LoanRequestDTO loanRequestDTO) {
        if (loanRequestDTO == null) {
            return null;
        }

        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setLoanRequestID(loanRequestDTO.getLoanRequestID());

        if (loanRequestDTO.getRegPersonDTO() != null) {
            RegPersonDTO regPersonDTO = loanRequestDTO.getRegPersonDTO();
            loanRequest.setRegPersonFirstName(regPersonDTO.getFirstName());
            loanRequest.setRegPersonMiddleName(regPersonDTO.getMiddleName());
            loanRequest.setRegPersonLastName(regPersonDTO.getLastName());
        }

        return loanRequest;
    }

    public LoanRequestDTO toDto(LoanRequest loanRequest) {
        if (loanRequest == null) {
            return null;
        }

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setLoanRequestID(loanRequest.getLoanRequestID());

        RegPersonDTO regPersonDTO = new RegPersonDTO();
        regPersonDTO.setFirstName(loanRequest.getRegPersonFirstName());
        regPersonDTO.setMiddleName(loanRequest.getRegPersonMiddleName());
        regPersonDTO.setLastName(loanRequest.getRegPersonLastName());
        loanRequestDTO.setRegPersonDTO(regPersonDTO);


        return loanRequestDTO;
    }
}
