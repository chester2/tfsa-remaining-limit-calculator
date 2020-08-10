package io.github.chester2.tfsaremaininglimitcalculator.dao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LimitDao {
    private final WebClient limitProvider;

    public LimitDao(WebClient limitProvider) {
        this.limitProvider = limitProvider;
    }

    private static class Limit {
        private final int year;
        private final BigDecimal amount;

        @JsonCreator
        public Limit(@JsonProperty("year") int year, @JsonProperty("amount") BigDecimal amount) {
            this.year = year;
            this.amount = amount;
        }

        public int getYear() {
            return year;
        }

        public BigDecimal getAmount() {
            return amount;
        }
    }

    public Map<Integer, BigDecimal> getLimits() {
        return limitProvider
            .get()
            .uri("/")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<Limit>>() {})
            .block()
            .stream()
            .collect(Collectors.toMap(Limit::getYear, Limit::getAmount));
    }
}
