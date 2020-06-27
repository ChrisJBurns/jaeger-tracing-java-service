package com.jaegartracingjavaservice;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final RestTemplate restTemplate;

    @ApiOperation(value = "Create Employee ", response = ResponseEntity.class)
    @RequestMapping(value = "/api/tutorial/1.0/employees", method = RequestMethod.POST)
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        log.info("Receive Request to add employee {}", employee);

        ResponseEntity<String> result = restTemplate.getForEntity("https://chrisjburns.com/", String.class);
        log.info("Body of request is: {}", result.getBody());
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
