package com.testTask.loanService.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

@Component
public class DetermineCountryServiceImplTest {
    @Mock
    DetermineCountryService determineCountryService = new DetermineCountryServiceImpl();

    @Before
    public void setup() {
        ReflectionTestUtils.setField(determineCountryService, "DETERMINE_COUNTRY_SERVICE_ADDRESS", "http://determineCounty.com/determine");
    }

    @Test
    public void determineCountryAssertReturnNotNull() {
        assertNotNull(determineCountryService.determineCountry("127.0.0.1"));
    }
}