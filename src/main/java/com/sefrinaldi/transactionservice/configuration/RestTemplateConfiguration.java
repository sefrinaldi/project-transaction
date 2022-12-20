package com.sefrinaldi.transactionservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@Slf4j
public class RestTemplateConfiguration {

    @Value("${http.outgoing.readTimeout}")
    private int readTimeout;

    @Value("${http.outgoing.connectionTimeout}")
    private int connectionTimeout;

    @Bean
    @Qualifier("serviceRestTemplate")
    public RestTemplate getInterServiceRestTemplate() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .build();
    }
}
