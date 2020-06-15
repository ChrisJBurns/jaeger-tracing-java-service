package com.jaegartracingjavaservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class Controller {

    @GetMapping("/{id}")
    private ResponseEntity<String> getEmployeeById(@PathVariable String id) {
        return ResponseEntity.ok("ID");
    }
}
