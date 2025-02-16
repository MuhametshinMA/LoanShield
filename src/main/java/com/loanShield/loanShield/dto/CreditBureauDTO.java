package com.loanShield.loanShield.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreditBureauDTO {

    private List<AccountInfoDTO> account_info;
    @JsonProperty("verified_name")
    private VerifiedNameDTO verifiedNameDTO;
}

