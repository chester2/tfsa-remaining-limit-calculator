package io.github.chester2.tfsaremaininglimitcalculator.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LimitProviderConfiguration {

    private @Value("${limit-provider-uri}") String providerUrl;

    @Bean
    public WebClient limitProvider() {
        return WebClient.create(providerUrl);
    }
}
