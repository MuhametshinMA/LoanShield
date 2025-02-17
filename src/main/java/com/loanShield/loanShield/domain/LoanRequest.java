package com.loanShield.loanShield.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_request_id")
    private String loanRequestID;

    @Column(name = "reg_person_first_name")
    private String regPersonFirstName;

    @Column(name = "reg_person_middle_name")
    private String regPersonMiddleName;

    @Column(name = "reg_person_last_name")
    private String regPersonLastName;

    @OneToOne
    @JoinColumn(name = "id_request_content")
    private RequestContent requestContent;

    @OneToOne(mappedBy = "loanRequest", cascade = CascadeType.ALL)
    private CreditBureau creditBureau;
}
