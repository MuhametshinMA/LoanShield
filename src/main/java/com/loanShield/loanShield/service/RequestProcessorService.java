package com.loanShield.loanShield.service;

public interface RequestProcessorService {

    void saveJsonData(Object data);
    Object getJsonData(Long id, Class<?> clazz);
}
