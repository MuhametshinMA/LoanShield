package com.loanShield.loanShield.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoanRequestDTO {

    @JsonProperty("loanRequestID")
    private String loanRequestID;
    @JsonProperty("regPerson")
    private RegPersonDTO regPersonDTO;
    @JsonProperty("creditBureau")
    private CreditBureauDTO creditBureauDTO;
}

