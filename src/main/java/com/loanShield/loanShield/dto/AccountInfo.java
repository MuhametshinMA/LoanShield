package com.loanShield.loanShield.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountInfo {

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_status")
    private String accountStatus;

    @JsonProperty("current_balance")
    private String currentBalance;

    @JsonProperty("date_opened")
    private LocalDate dateOpened;

    @JsonProperty("days_in_arrears")
    private Integer daysInArrears;

    @JsonProperty("delinquency_code")
    private String delinquencyCode;

    @JsonProperty("highest_days_in_arrears")
    private Integer highestDaysInArrears;

    @JsonProperty("is_your_account")
    private Boolean isYourAccount;

    @JsonProperty("last_payment_amount")
    private String lastPaymentAmount;

    @JsonProperty("last_payment_date")
    private LocalDate lastPaymentDate;

    @JsonProperty("loaded_at")
    private LocalDate loadedAt;

    @JsonProperty("original_amount")
    private String originalAmount;

    @JsonProperty("overdue_balance")
    private String overdueBalance;

    @JsonProperty("overdue_date")
    private LocalDate overdueDate;

    @JsonProperty("product_type_id")
    private Integer productTypeId;
}
