package io.github.chester2.tfsaremaininglimitcalculator.model;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Calculator {

    private Map<Integer, BigDecimal> limits;
    private int currentYear;
    private List<Integer> residencyYears;
    private List<Transaction> transactions;

    public void setLimits(Map<Integer, BigDecimal> limits) {
        this.limits = limits;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public void setResidencyYears(List<Integer> residencyYears) {
        this.residencyYears = residencyYears;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal execute() {
        if (limits == null) throw new IllegalStateException("limits not set");
        if (residencyYears == null) throw new IllegalStateException("residencyYears not set");
        if (transactions == null) throw new IllegalStateException("transactions not set");
        if (!limits.containsKey(currentYear))
            throw new IllegalStateException("Current year does not have an associated limit");

        Map<Integer, BigDecimal> relevantLimits = residencyYears
            .stream()
            .filter(y -> y <= currentYear && limits.containsKey(y))
            .collect(Collectors.toMap(x -> x, limits::get));

        if (!relevantLimits.containsKey(currentYear)) return BigDecimal.ZERO;

        BigDecimal remainingLimit = relevantLimits
            .values()
            .stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        for (Transaction t : transactions) {
            if (relevantLimits.containsKey(t.getYear())) {
                if (t.getAmount().compareTo(BigDecimal.ZERO) > 0)
                    remainingLimit = remainingLimit.subtract(t.getAmount());
                else if (t.getYear() < currentYear)
                    remainingLimit = remainingLimit.subtract(t.getAmount());
            }
        }

        return remainingLimit;
    }
}
