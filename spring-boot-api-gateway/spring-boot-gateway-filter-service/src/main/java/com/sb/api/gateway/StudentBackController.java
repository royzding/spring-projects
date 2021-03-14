package com.sb.api.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class StudentBackController {

    @GetMapping("/studentFallback")
    public ResponseEntity<String> orderServiceFallback() {
        return new ResponseEntity<>("We are facing some issue with student. Please contact support",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
