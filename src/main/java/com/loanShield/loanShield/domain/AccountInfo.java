package com.loanShield.loanShield.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "account_info")
@NoArgsConstructor
@Getter
@Setter
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_credit_bureau", nullable = false)
    private CreditBureau creditBureau;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "date_opened")
    private LocalDate dateOpened;

    @Column(name = "days_in_arrears")
    private Integer daysInArrears;

    @Column(name = "delinquency_code")
    private String delinquencyCode;

    @Column(name = "highest_days_in_arrears")
    private Integer highestDaysInArrears;

    @Column(name = "is_your_account")
    private Boolean isYourAccount;

    @Column(name = "last_payment_amount")
    private BigDecimal lastPaymentAmount;

    @Column(name = "last_payment_date")
    private LocalDate lastPaymentDate;

    @Column(name = "loaded_at")
    private LocalDate loadedAt;

    @Column(name = "original_amount")
    private BigDecimal originalAmount;

    @Column(name = "overdue_balance")
    private BigDecimal overdueBalance;

    @Column(name = "overdue_date")
    private LocalDate overdueDate;

    @Column(name = "product_type_id")
    private Integer productTypeId;
}
