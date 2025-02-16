package com.loanShield.loanShield.repository;

import com.loanShield.loanShield.domain.LoanRequest;
import com.loanShield.loanShield.domain.RequestContent;
import com.loanShield.loanShield.dto.LoanRequestDTO;
import com.loanShield.loanShield.mapper.AccountInfoMapper;
import com.loanShield.loanShield.mapper.CreditBureauMapper;
import com.loanShield.loanShield.mapper.LoanRequestMapper;
import com.loanShield.loanShield.service.LoanRequestService;
import com.loanShield.loanShield.service.LoanRequestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ComponentScan({
        "com.loanShield.loanShield.mapper",
        "com.loanShield.loanShield.repository"
})
public class RequestContentRepositoryTest {

    @TestConfiguration
    static class LoanRequestServiceTestConfig {

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public LoanRequestMapper loanRequestMapper() {
            return new LoanRequestMapper();
        }

        @Bean
        public CreditBureauMapper creditBureauMapper() {
            return new CreditBureauMapper();
        }

        @Bean
        public AccountInfoMapper accountInfoMapper() {
            return new AccountInfoMapper();
        }

        @Bean
        public LoanRequestService loanRequestService(
                LoanRequestMapper loanRequestMapper,
                CreditBureauMapper creditBureauMapper,
                AccountInfoMapper accountInfoMapper,
                LoanRequestRepository loanRequestRepository) {
            return new LoanRequestServiceImpl(loanRequestMapper, creditBureauMapper, accountInfoMapper, loanRequestRepository);
        }
    }

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer
            = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private RequestContentRepository requestContentRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRequestService loanRequestService;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    private int sizeOfRequest;

    private void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ClassPathResource resource = new ClassPathResource("test-data.json");
        InputStream inputStream = resource.getInputStream();
        List<Object> jsonDataList = objectMapper.readValue(inputStream, new TypeReference<List<Object>>() {
        });

        sizeOfRequest = jsonDataList.size();

        for (Object jsonData : jsonDataList) {
            RequestContent requestContent = new RequestContent();
            requestContent.setJsonData(objectMapper.writeValueAsString(jsonData));
            requestContentRepository.save(requestContent);
        }
    }

    @Test
    public void testCheckSizeRequestContent() throws IOException {

        init();

        List<RequestContent> allContents = requestContentRepository.findAll();
        Assertions.assertEquals(sizeOfRequest, allContents.size());
    }

    @Test
    public void testParseJsonFromRequestContent() throws IOException {

        init();

        List<RequestContent> allContents = requestContentRepository.findAll();

        List<LoanRequestDTO> loanRequestDTOS =
                allContents.stream().map(RequestContent::getLoanRequestDTO).toList();
        loanRequestDTOS.forEach(System.out::println);
        Assertions.assertEquals(sizeOfRequest, loanRequestDTOS.size());

//        loanRequestDTOS.get(0).setCreditBureauDTO();

        loanRequestService.saveLoanRequest(loanRequestDTOS.get(0), allContents.get(0));
//        List<LoanRequest> loanRequests = loanRequest
    }
}
