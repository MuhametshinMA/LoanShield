package com.loanShield.loanShield.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanShield.loanShield.domain.RequestContent;
import com.loanShield.loanShield.repository.RequestContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestProcessorServiceImpl implements RequestProcessorService{

    private final RequestContentRepository requestContentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveJsonData(Object data) {
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            RequestContent requestContent = new RequestContent();
            requestContent.setJsonData(jsonData);
            requestContentRepository.save(requestContent);
        } catch (JsonProcessingException e) {
            //TODO: обьработать глобальным обработчиком
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getJsonData(Long id, Class<?> clazz) {
        RequestContent requestContent = requestContentRepository.findById(id).orElse(null);
        if (requestContent != null) {
            try {
                return objectMapper.readValue(requestContent.getJsonData(), clazz);
            } catch (Exception e) {
                //TODO: обьработать глобальным обработчиком
                e.printStackTrace();
            }
        }
        return null;
    }
}
