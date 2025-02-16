package com.loanShield.loanShield.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerifiedNameDTO {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("other_name")
    private String otherName;
    @JsonProperty("surname")
    private String surname;
}
