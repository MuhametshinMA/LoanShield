package com.loanShield.loanShield.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credit_bureau")
@NoArgsConstructor
@Getter
@Setter
public class CreditBureau {

    @Id
    @Column(name = "id_loan_request")
    private Long loanRequestId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_loan_request")
    private LoanRequest loanRequest;

    @Column(name = "verified_name_firstName")
    private String verifiedNameFirstName;

    @Column(name = "verified_name_otherName")
    private String verifiedNameOtherName;

    @Column(name = "verified_name_surname")
    private String verifiedNameSurname;
}
