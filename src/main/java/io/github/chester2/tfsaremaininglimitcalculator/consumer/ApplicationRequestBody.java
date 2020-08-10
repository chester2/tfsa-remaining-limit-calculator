package io.github.chester2.tfsaremaininglimitcalculator.consumer;

import io.github.chester2.tfsaremaininglimitcalculator.model.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ApplicationRequestBody {
    private final int currentYear;
    private final List<Integer> residencyYears;
    private final List<Transaction> transactions;

    public ApplicationRequestBody(
        int currentYear,
        List<Integer> residencyYears,
        List<Transaction> transactions
    ) {
        this.currentYear = currentYear;
        this.residencyYears = Collections.unmodifiableList(Objects.requireNonNull(residencyYears));
        this.transactions = Collections.unmodifiableList(Objects.requireNonNull(transactions));
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public List<Integer> getResidencyYears() {
        return residencyYears;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
