package com.patientAssessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import exception.FeignErrorDecoder;

/**
 * The Class FeignErrorDecoderBean.
 */
@Configuration
public class FeignErrorDecoderBean {

    /**
     * My error decoder.
     *
     * @return the feign error decoder
     */
    @Bean
    public FeignErrorDecoder myErrorDecoder() {
        return new FeignErrorDecoder();
    }
}