package com.loanShield.loanShield.repository;

import com.loanShield.loanShield.config.NamingStrategyConfig;
import com.loanShield.loanShield.domain.LoanRequest;
import com.loanShield.loanShield.domain.RequestContent;
import com.loanShield.loanShield.dto.LoanRequestDTO;
import com.loanShield.loanShield.mapper.AccountInfoMapper;
import com.loanShield.loanShield.mapper.CreditBureauMapper;
import com.loanShield.loanShield.mapper.LoanRequestMapper;
import com.loanShield.loanShield.service.LoanRequestService;
import com.loanShield.loanShield.service.LoanRequestServiceImpl;
import com.loanShield.loanShield.utils.LoanShieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
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
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ComponentScan({
        "com.loanShield.loanShield.mapper",
        "com.loanShield.loanShield.repository"
})
@Import(NamingStrategyConfig.class)
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

        for (int i = 0; i < allContents.size(); i++) {
            loanRequestService.saveLoanRequest(loanRequestDTOS.get(i), allContents.get(i));
        }

        List<LoanRequest> loanRequestList = loanRequestRepository.findAll();
        Assertions.assertEquals(sizeOfRequest, loanRequestList.size());

        for (LoanRequest loanRequest : loanRequestList) {

            List<String> regPersonNamePairs = LoanShieldUtils.generatePairs(
                    loanRequest.getRegPersonFirstName(),
                    loanRequest.getRegPersonMiddleName(),
                    loanRequest.getRegPersonLastName()
            );

            List<String> verifyNamePairs = LoanShieldUtils.generatePairs(
                    loanRequest.getCreditBureau().getVerifiedNameFirstName(),
                    loanRequest.getCreditBureau().getVerifiedNameOtherName(),
                    loanRequest.getCreditBureau().getVerifiedNameSurname());

            boolean factor = LoanShieldUtils.calcFactor(regPersonNamePairs, verifyNamePairs);
        }
    }

    @Test
    public void testGeneratePairs() {
        List<String> expected = Arrays.asList("AB", "AC", "AD", "BA", "BC", "BD", "CA", "CB", "CD", "DA", "DB", "DC");

        List<String> actual = LoanShieldUtils.generatePairs("A", "B", "C", "D");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testIdenticalStrings() {
        String str1 = "kitten";
        String str2 = "kitten";
        double expectedDistance = 0.0;
        double actualDistance = LoanShieldUtils.calculateNormalizedDistance(str1, str2);
        Assertions.assertEquals(expectedDistance, actualDistance, 0.0001);
    }

    @Test
    public void testOneInsertion() {
        String str1 = "kitten";
        String str2 = "kittens";
        double expectedDistance = 1.0 / 7;
        double actualDistance = LoanShieldUtils.calculateNormalizedDistance(str1, str2);
        Assertions.assertEquals(expectedDistance, actualDistance, 0.0001);
    }

    @Test
    public void testOneDeletion() {
        String str1 = "kittens";
        String str2 = "kitten";
        double expectedDistance = 1.0 / 7;
        double actualDistance = LoanShieldUtils.calculateNormalizedDistance(str1, str2);
        Assertions.assertEquals(expectedDistance, actualDistance, 0.0001);
    }

    @Test
    public void testOneSubstitution() {
        String str1 = "kitten";
        String str2 = "sitten";
        double expectedDistance = 1.0 / 6;
        double actualDistance = LoanShieldUtils.calculateNormalizedDistance(str1, str2);
        Assertions.assertEquals(expectedDistance, actualDistance, 0.0001);
    }

    @Test
    public void testMultipleOperations() {
        String str1 = "kitten";
        String str2 = "sitting";
        double expectedDistance = 3.0 / 7;
        double actualDistance = LoanShieldUtils.calculateNormalizedDistance(str1, str2);
        Assertions.assertEquals(expectedDistance, actualDistance, 0.0001);
    }

    @Test
    public void testEmptyString() {
        String str1 = "kitten";
        String str2 = "";
        double expectedDistance = 6.0 / 6;
        double actualDistance = LoanShieldUtils.calculateNormalizedDistance(str1, str2);
        Assertions.assertEquals(expectedDistance, actualDistance, 0.0001);
    }

    @Test
    public void testDifferentLengths() {
        String str1 = "abc";
        String str2 = "defghi";
        double expectedDistance = 6.0 / 6;
        double actualDistance = LoanShieldUtils.calculateNormalizedDistance(str1, str2);
        Assertions.assertEquals(expectedDistance, actualDistance, 0.0001);
    }

    @Test
    public void testCompletelyDifferentStrings() {
        String str1 = "apple";
        String str2 = "banana";
        double expectedDistance = 5.0 / 6;
        double actualDistance = LoanShieldUtils.calculateNormalizedDistance(str1, str2);
        Assertions.assertEquals(expectedDistance, actualDistance, 0.0001);
    }

    @Test
    public void testCalcFactorIdenticalStrings() {
        List<String> arg1 = Arrays.asList("kitten", "sitting");
        List<String> arg2 = Arrays.asList("kitten", "sitting");

        boolean result = LoanShieldUtils.calcFactor(arg1, arg2);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCalcFactorSimilarStrings() {
        List<String> arg1 = Arrays.asList("kitten", "sitting");
        List<String> arg2 = Arrays.asList("kittens", "sittin");

        boolean result = LoanShieldUtils.calcFactor(arg1, arg2);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCalcFactorSingleElementLists() {
        List<String> arg1 = Arrays.asList("A");
        List<String> arg2 = Arrays.asList("B");

        boolean result = LoanShieldUtils.calcFactor(arg1, arg2);
        Assertions.assertFalse(result);
    }
}
