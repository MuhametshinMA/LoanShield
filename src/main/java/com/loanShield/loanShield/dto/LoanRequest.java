package com.loanShield.loanShield.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoanRequest {

    @JsonProperty("loanRequestID")
    private String loanRequestID;
    @JsonProperty("regPerson")
    private RegPerson regPerson;
    @JsonProperty("creditBureau")
    private CreditBureau creditBureau;
    @JsonProperty("verifiedName")
    private VerifiedName verifiedName;
}

