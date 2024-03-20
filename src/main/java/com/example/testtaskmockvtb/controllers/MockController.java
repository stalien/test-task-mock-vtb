package com.example.testtaskmockvtb.controllers;

import com.example.testtaskmockvtb.models.MockRequest;
import com.example.testtaskmockvtb.models.MockResponse;
import com.example.testtaskmockvtb.services.MockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class MockController {

    private final MockService mockService;

    public MockController(MockService mockService) {
        this.mockService = mockService;
    }

    @PostMapping("/mock")
    public ResponseEntity<?> handlePostRequest(@RequestBody MockRequest mockRequest) throws InterruptedException {
        log.info("Request: {}", mockRequest);

        MockResponse mockResponse = mockService.generateResponse(mockRequest);
        log.info("Response: {}", mockResponse);

        if (mockService.isError()) {
            log.error("Status: {}", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(Map.of("error", "Произошла ошибка на сервере"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            log.info("Status: {}", HttpStatus.OK);
            return new ResponseEntity<>(mockResponse, HttpStatus.OK);
        }
    }
}
