package com.loanShield.loanShield.service;

import com.loanShield.loanShield.domain.AccountInfo;
import com.loanShield.loanShield.domain.CreditBureau;
import com.loanShield.loanShield.domain.LoanRequest;
import com.loanShield.loanShield.domain.RequestContent;
import com.loanShield.loanShield.dto.LoanRequestDTO;
import com.loanShield.loanShield.mapper.AccountInfoMapper;
import com.loanShield.loanShield.mapper.CreditBureauMapper;
import com.loanShield.loanShield.mapper.LoanRequestMapper;
import com.loanShield.loanShield.repository.LoanRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestMapper loanRequestMapper;
    private final CreditBureauMapper creditBureauMapper;
    private final AccountInfoMapper accountInfoMapper;
    private final LoanRequestRepository loanRequestRepository;

    @Override
    public void saveLoanRequest(LoanRequestDTO loanRequestDTO, RequestContent requestContent) {

        LoanRequest loanRequest = loanRequestMapper.toEntity(loanRequestDTO);
        CreditBureau creditBureau = creditBureauMapper.toEntity(loanRequestDTO.getCreditBureauDTO());

        creditBureau.setLoanRequest(loanRequest);
//        List<AccountInfo> accnfoMapper::toEntity)
//                .toList();
//        creditBureau.setAccoountInfos = loanRequestDTO.getCreditBureauDTO().getAccount_info().stream()
//                .map(accountIuntInfos(accountInfos);
        loanRequest.setCreditBureau(creditBureau);
        loanRequest.setRequestContent(requestContent);


        loanRequestRepository.save(loanRequest);
    }
}
