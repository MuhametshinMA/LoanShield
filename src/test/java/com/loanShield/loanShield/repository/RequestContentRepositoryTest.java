package com.loanShield.loanShield.repository;

import com.loanShield.loanShield.domain.RequestContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest
@Testcontainers
public class RequestContentRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private RequestContentRepository requestContentRepository;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void testSaveMultipleJsonFromFile() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Load JSON data from file
//        ClassPathResource resource = new ClassPathResource("test-data.json");
//        InputStream inputStream = resource.getInputStream();
//        List<Object> jsonDataList = objectMapper.readValue(inputStream, new TypeReference<List<Object>>() {});
//
////         Save JSON data to database
//        for (Object jsonData : jsonDataList) {
//            RequestContent requestContent = new RequestContent();
//            requestContent.setJsonData(objectMapper.writeValueAsString(jsonData));
//            requestContentRepository.save(requestContent);
//        }
//
//        // Retrieve JSON data from database and verify
//        List<RequestContent> allContents = requestContentRepository.findAll();
//        Assertions.assertEquals(3, allContents.size());
//
//        for (int i = 0; i < jsonDataList.size(); i++) {
//            Assertions.assertEquals(objectMapper.writeValueAsString(jsonDataList.get(i)), allContents.get(i).getJsonData());
//        }
    }
}
