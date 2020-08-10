package io.github.chester2.tfsaremaininglimitcalculator.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationTraceRepository implements HttpTraceRepository {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationTraceRepository.class);

    @Override
    public List<HttpTrace> findAll() {
        return null;
    }

    @Override
    public void add(HttpTrace trace) {
        try {
            logger.info(new ObjectMapper().writeValueAsString(trace));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
