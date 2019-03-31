package com.testTask.loanService.utils;

import com.testTask.loanService.exceptions.LimitExceededException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

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
    public void validateLimit() throws Exception {
        long startTime = ZonedDateTime.now().getSecond();
        for (int i = 0; i < limit*2; i++) {
           try {
               limitValidator.validateLimit("ru");
           } catch (LimitExceededException e) {
               if (i>limit)
                   return;
               else
                   throw new Exception();
           }
        }
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;
        System.out.println(elapsedTimeSeconds);
    }
}