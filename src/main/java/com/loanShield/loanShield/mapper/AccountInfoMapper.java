package com.loanShield.loanShield.mapper;

import com.loanShield.loanShield.domain.AccountInfo;
import com.loanShield.loanShield.dto.AccountInfoDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountInfoMapper {

    public AccountInfo toEntity(AccountInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        AccountInfo entity = new AccountInfo();
        entity.setAccountNumber(dto.getAccountNumber());
        entity.setAccountStatus(dto.getAccountStatus());
        entity.setDaysInArrears(dto.getDaysInArrears());
        entity.setDelinquencyCode(dto.getDelinquencyCode());
        entity.setHighestDaysInArrears(dto.getHighestDaysInArrears());
        entity.setIsYourAccount(dto.getIsYourAccount());
        entity.setDateOpened(dto.getDateOpened());
        entity.setLastPaymentDate(dto.getLastPaymentDate());
        entity.setLoadedAt(dto.getLoadedAt());
        entity.setOverdueDate(dto.getOverdueDate());
        entity.setProductTypeId(dto.getProductTypeId());

        entity.setCurrentBalance(convertStringToBigDecimal(dto.getCurrentBalance()));
        entity.setLastPaymentAmount(convertStringToBigDecimal(dto.getLastPaymentAmount()));
        entity.setOriginalAmount(convertStringToBigDecimal(dto.getOriginalAmount()));
        entity.setOverdueBalance(convertStringToBigDecimal(dto.getOverdueBalance()));

        return entity;
    }

    public AccountInfoDTO toDto(AccountInfo entity) {
        if (entity == null) {
            return null;
        }

        AccountInfoDTO dto = new AccountInfoDTO();
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setAccountStatus(entity.getAccountStatus());
        dto.setDaysInArrears(entity.getDaysInArrears());
        dto.setDelinquencyCode(entity.getDelinquencyCode());
        dto.setHighestDaysInArrears(entity.getHighestDaysInArrears());
        dto.setIsYourAccount(entity.getIsYourAccount());
        dto.setDateOpened(entity.getDateOpened());
        dto.setLastPaymentDate(entity.getLastPaymentDate());
        dto.setLoadedAt(entity.getLoadedAt());
        dto.setOverdueDate(entity.getOverdueDate());
        dto.setProductTypeId(entity.getProductTypeId());

        dto.setCurrentBalance(convertBigDecimalToString(entity.getCurrentBalance()));
        dto.setLastPaymentAmount(convertBigDecimalToString(entity.getLastPaymentAmount()));
        dto.setOriginalAmount(convertBigDecimalToString(entity.getOriginalAmount()));
        dto.setOverdueBalance(convertBigDecimalToString(entity.getOverdueBalance()));

        return dto;
    }

    private BigDecimal convertStringToBigDecimal(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String convertBigDecimalToString(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}
