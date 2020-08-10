package io.github.chester2.tfsaremaininglimitcalculator.consumer;

import io.github.chester2.tfsaremaininglimitcalculator.dao.LimitDao;
import io.github.chester2.tfsaremaininglimitcalculator.exceptions.BadRequestException;
import io.github.chester2.tfsaremaininglimitcalculator.model.Calculator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ApplicationController {
    private final LimitDao limitDao;
    private final Calculator calculator;

    public ApplicationController(LimitDao limitDao, Calculator calculator) {
        this.limitDao = limitDao;
        this.calculator = calculator;
    }

    @PostMapping("/")
    public BigDecimal calculateRemaining(@RequestBody ApplicationRequestBody requestBody) {
        calculator.setLimits(limitDao.getLimits());
        calculator.setCurrentYear(requestBody.getCurrentYear());
        calculator.setResidencyYears(requestBody.getResidencyYears());
        calculator.setTransactions(requestBody.getTransactions());
        try {
            return calculator.execute();
        } catch (IllegalStateException e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }
}
