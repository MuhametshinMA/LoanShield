package com.loanShield.loanShield.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "request_content")
@NoArgsConstructor
@Getter
@Setter
public class RequestContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "json_data", columnDefinition = "jsonb")
    private String jsonData;

    @OneToOne(mappedBy = "requestContent", cascade = CascadeType.ALL, orphanRemoval = true)
    private LoanRequest loanRequest;
}
