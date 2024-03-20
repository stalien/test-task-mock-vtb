package com.example.testtaskmockvtb.services;

import com.example.testtaskmockvtb.models.MockRequest;
import com.example.testtaskmockvtb.models.MockResponse;
import com.example.testtaskmockvtb.models.MotherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MockService {

    @Value("${mockResponse.delay}")
    private int mockResponseDelay;
    @Value("${mockResponse.delay.on}")
    private boolean mockResponseDelayOn;
    @Value("${error.rate}")
    private int errorRate;

    public MockResponse generateResponse(MockRequest mockRequest) throws InterruptedException {
        Random random = new Random();

        // Эмулируемая задержка ответа
        int delay;
        do {
            double val = random.nextGaussian() * 100 + mockResponseDelay;
            delay = (int) Math.round(val);
        } while (delay <= 0);
        if(mockResponseDelayOn) {
            TimeUnit.MILLISECONDS.sleep(delay);
        }

        MotherDTO changedAgeMother = mockRequest.getMother();
        changedAgeMother.setAge(54);

        return MockResponse.builder()
                .name(mockRequest.getName())
                .age(54)
                .mother(changedAgeMother)
                .children(mockRequest.getChildren())
                .married(mockRequest.isMarried())
                .dog(mockRequest.getDog())
                .build();
    }

    public boolean isError() {
        // Эмуляция заданного процента ошибок
        Random random = new Random();
        int randomValue = random.nextInt(100);
        return randomValue < errorRate;
    }
}
