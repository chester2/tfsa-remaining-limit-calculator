package io.github.chester2.tfsaremaininglimitcalculator.model;

import java.math.BigDecimal;

public class Transaction {
    private final int year;
    private final BigDecimal amount;

    public Transaction(int year, BigDecimal amount) {
        this.year = year;
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "year=" + year +
            ", amount=" + amount +
            '}';
    }
}
