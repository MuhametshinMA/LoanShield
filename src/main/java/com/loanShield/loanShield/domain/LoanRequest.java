package com.loanShield.loanShield.domain;

import jakarta.persistence.CascadeType;
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
@Table(name = "loan_request")
@NoArgsConstructor
@Getter
@Setter
public class LoanRequest {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "loanRequestID")
    private String loanRequestID;

    @Column(name = "regPerson_firstName")
    private String regPersonFirstName;

    @Column(name = "regPerson_middleName")
    private String regPersonMiddleName;

    @Column(name = "regPerson_lastName")
    private String regPersonLastName;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private RequestContent requestContent;

    @OneToOne(mappedBy = "loanRequest", cascade = CascadeType.ALL)
    private CreditBureau creditBureau;
}
