package io.github.chester2.tfsaremaininglimitcalculator.consumer;

import io.github.chester2.tfsaremaininglimitcalculator.dao.LimitDao;
import io.github.chester2.tfsaremaininglimitcalculator.model.Calculator;
import io.github.chester2.tfsaremaininglimitcalculator.model.Transaction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationControllerTest {

    private LimitDao limitDaoMock() {
        LimitDao limitDao = Mockito.mock(LimitDao.class);
        Mockito.when(limitDao.getLimits()).thenReturn(new HashMap<>());
        return limitDao;
    }

    private Calculator calculatorMock() {
        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.execute()).thenReturn(BigDecimal.ONE);
        return calculator;
    }

    @Test
    public void call() {
        ApplicationController applicationController = new ApplicationController(limitDaoMock(), calculatorMock());
        BigDecimal result = applicationController.calculateRemaining(
            new ApplicationRequestBody(
                3,
                Arrays.asList(1, 2, 3),
                Arrays.asList(
                    new Transaction(1, BigDecimal.valueOf(1000)),
                    new Transaction(2, BigDecimal.valueOf(1000))
                )
            )
        );

        assertEquals(BigDecimal.ONE, result);
    }
}