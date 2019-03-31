package com.testTask.loanService.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

@Component
public class LimitValidatorImplTest {
    @Mock
    LimitValidator limitValidator = new LimitValidatorImpl();

    private Integer limit = 100;

    @Before
    public void setup(){
        ReflectionTestUtils.setField(limitValidator, "MAX_APPLICATIONS_PER_SECOND", limit);
    }

    @Test
    public void validateLimit() {
        long startTime = ZonedDateTime.now().getSecond();
        int multiplier = 2;
        for (int i = 0; i < limit*multiplier; i++) {
               limitValidator.validateLimit("ru");
        }
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;
        assertEquals(multiplier, elapsedTimeSeconds);
    }
}