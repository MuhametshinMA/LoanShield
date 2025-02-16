package com.loanShield.loanShield.config;

import com.loanShield.loanShield.repository.RequestContentRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableAutoConfiguration
@EntityScan(basePackages = "com.loanShield.loanShield.domain")
@EnableJpaRepositories(basePackages = "com.loanShield.loanShield.repository")
public class RequestContentRepositoryTestConfig {

}
