package com.jaegartracingjavaservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SalaryGradeService {

    public Mono<SalaryGrade> getSalaryGradeForOccupation(String occupation) {
        return client()
                .get()
                .uri("/get-salary-grade/" + occupation)
                .retrieve()
                .bodyToMono(SalaryGrade.class);
    }

    private WebClient client () {
        return WebClient.create("http://jaeger-tracing-python-service:8092");
    }
}
