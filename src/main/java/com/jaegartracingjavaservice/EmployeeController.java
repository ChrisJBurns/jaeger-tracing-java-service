package com.jaegartracingjavaservice;

import com.jaegartracingjavaservice.service.SalaryGrade;
import com.jaegartracingjavaservice.service.SalaryGradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final SalaryGradeService salaryGradeService;

    @RequestMapping(value = "/api/tutorial/1.0/employees", method = RequestMethod.POST)
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        log.info("Receive Request to add employee {}", employee);

        Mono<SalaryGrade> salaryGrade = salaryGradeService.getSalaryGradeForOccupation(employee.getOccupation());
        Mono<Object> result = salaryGrade.flatMap(grade -> makePost(createEmployeeGo(employee, grade.getSalaryGrade())));

        result
            .subscribe(
                element -> log.info("Employee object: {} ", element.toString()),
                error -> log.error(error.getMessage()),
                () -> log.info("Request completed.")
            );

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/tutorial/1.0/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity getEmployee(@PathVariable("id") String id) {
        log.info("Receive Request to get employee {}", id);

        makeCall()
            .subscribe(
                log::info,
                error -> log.error(error.getMessage()),
                () -> log.info("Request completed.")
            );

        return new ResponseEntity(null, HttpStatus.OK);
    }

    public Mono<EmployeeGo> makePost(EmployeeGo employeeGo) {
        return client()
                .post()
                .uri("/employee")
                .body(Mono.just(employeeGo), EmployeeGo.class)
                .retrieve()
                .bodyToMono(EmployeeGo.class);
    }

    private EmployeeGo createEmployeeGo(Employee employee, String salaryGrade) {
        EmployeeGo employeeGo = new EmployeeGo();
        employeeGo.setFirstName(employee.getFirstName());
        employeeGo.setLastName(employee.getLastName());
        employeeGo.setOccupation(employee.getOccupation());
        employeeGo.setSalaryGrade(salaryGrade);
        return employeeGo;
    }

    public Mono<String> makeCall() {
        return client()
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }

    private WebClient client () {
        return WebClient.create("http://jaeger-tracing-go-service:8091");
    }
}
