package com.example.testtaskmockvtb.controllers;

import com.example.testtaskmockvtb.models.MockRequest;
import com.example.testtaskmockvtb.models.MockResponse;
import com.example.testtaskmockvtb.models.MotherDTO;
import com.example.testtaskmockvtb.services.MockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MockControllerTest {

    @Autowired
    private MockService mockService;

    @Test
    void handlePostRequest() throws InterruptedException {

        MotherDTO motherIn = new MotherDTO();
        motherIn.setName("Ольга");
        motherIn.setAge(58);

        MotherDTO motherOut = new MotherDTO();
        motherOut.setName("Ольга");
        motherOut.setAge(54);

        MockRequest mockRequest = MockRequest.builder()
                .name("Иван")
                .age(37)
                .mother(motherIn)
                .children(List.of("Маша", "Игорь", "Таня"))
                .married(true)
                .dog(null)
                .build();

        MockResponse ExpectedResponse = MockResponse.builder()
                .name("Иван")
                .age(54)
                .mother(motherOut)
                .children(List.of("Маша", "Игорь", "Таня"))
                .married(true)
                .dog(null)
                .build();

        MockResponse actualResponse = mockService.generateResponse(mockRequest);

        assertEquals(ExpectedResponse, actualResponse);
    }
}