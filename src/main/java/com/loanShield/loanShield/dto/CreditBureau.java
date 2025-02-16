package com.loanShield.loanShield.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreditBureau {

    private List<AccountInfo> account_info;
}

