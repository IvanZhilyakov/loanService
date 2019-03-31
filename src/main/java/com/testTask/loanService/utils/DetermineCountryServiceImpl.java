package com.testTask.loanService.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DetermineCountryServiceImpl implements DetermineCountryService {
    private static final String DEFAULT_COUNTRY = "ru";

    @Value("${determineCountryServiceAddress}")
    private String DETERMINE_COUNTRY_SERVICE_ADDRESS;
    @Override
    public String determineCountry(String ip) {
//        restTemplateBuilder todo установить таймауты
//                .setConnectTimeout(...)
//           .setReadTimeout(...)
//           .build();

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", ip);
        RestTemplate restTemplate = new RestTemplate();
        String result = null;
        try {
            result = restTemplate.getForObject(DETERMINE_COUNTRY_SERVICE_ADDRESS, String.class, params);
        } catch (RestClientException e) {
            log.warn("Determine country service is unreachable");
            result = DEFAULT_COUNTRY;
        }
        return result;
    }
}
