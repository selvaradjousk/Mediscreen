package com.patientAssessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import exception.FeignErrorDecoder;

@Configuration
public class FeignErrorDecoderBean {

    @Bean
    public FeignErrorDecoder myErrorDecoder() {
        return new FeignErrorDecoder();
    }
}