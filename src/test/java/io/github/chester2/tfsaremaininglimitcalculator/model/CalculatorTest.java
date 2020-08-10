package io.github.chester2.tfsaremaininglimitcalculator.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    // For testing, we will have limits for years 0 to 9 where the limit for year x is 10x.
    private final Calculator calculator = new Calculator();
    {
        Map<Integer, BigDecimal> limits = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            limits.put(i, BigDecimal.valueOf(i * 10));
        }
        calculator.setLimits(limits);
    }

    private BigDecimal testCommon(
        int currentYear,
        List<Integer> residencyYears,
        List<Transaction> transactions
    ) {
        calculator.setCurrentYear(currentYear);
        calculator.setResidencyYears(residencyYears);
        calculator.setTransactions(transactions);
        return calculator.execute();
    }

    @Test
    public void execute_normal_success() {
        BigDecimal actual = testCommon(
            8,
            Arrays.asList(0, 2, 4, 6, 8, 11),                    // current year = 8
            Arrays.asList(
                new Transaction(-1, BigDecimal.TEN),        // ignored
                new Transaction(4, BigDecimal.TEN),
                new Transaction(5, BigDecimal.TEN),         // ignored
                new Transaction(6, BigDecimal.TEN),
                new Transaction(6, BigDecimal.ONE.negate()),
                new Transaction(8, BigDecimal.TEN),
                new Transaction(8, BigDecimal.ONE.negate()) // ignored
            )
        );

        BigDecimal expected = BigDecimal.valueOf(
            (0 + 2 + 4 + 6 + 8) * 10    // official limits
                - 10                        // year 4 contribution
                - 10                        // year 6 contribution
                + 1                         // year 6 withdrawal
                - 10                        // year 8 contribution
        );

        assertEquals(expected, actual);
    }

    @Test
    public void execute_currentYearNotResidencyYear_success() {
        assertEquals(BigDecimal.ZERO, testCommon(9, Arrays.asList(1), new ArrayList<>()));
    }
}