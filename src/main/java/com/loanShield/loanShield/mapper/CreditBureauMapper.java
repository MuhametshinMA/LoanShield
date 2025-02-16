package com.loanShield.loanShield.mapper;

import com.loanShield.loanShield.domain.CreditBureau;
import com.loanShield.loanShield.dto.CreditBureauDTO;
import com.loanShield.loanShield.dto.VerifiedNameDTO;
import org.springframework.stereotype.Component;

@Component
public class CreditBureauMapper {

    public CreditBureau toEntity(CreditBureauDTO dto) {
        if (dto == null) {
            return null;
        }

        CreditBureau creditBureau = new CreditBureau();

        if (dto.getVerifiedNameDTO() != null) {
            creditBureau.setVerifiedNameFirstName(dto.getVerifiedNameDTO().getFirstName());
            creditBureau.setVerifiedNameOtherName(dto.getVerifiedNameDTO().getOtherName());
            creditBureau.setVerifiedNameSurname(dto.getVerifiedNameDTO().getSurname());
        }

        return creditBureau;
    }

    public CreditBureauDTO toDto(CreditBureau creditBureau) {
        if (creditBureau == null) {
            return null;
        }

        CreditBureauDTO dto = new CreditBureauDTO();
        VerifiedNameDTO verifiedNameDTO = new VerifiedNameDTO();

        // Устанавливаем поля VerifiedNameDTO
        verifiedNameDTO.setFirstName(creditBureau.getVerifiedNameFirstName());
        verifiedNameDTO.setOtherName(creditBureau.getVerifiedNameOtherName());
        verifiedNameDTO.setSurname(creditBureau.getVerifiedNameSurname());

        dto.setVerifiedNameDTO(verifiedNameDTO);

        return dto;
    }
}
