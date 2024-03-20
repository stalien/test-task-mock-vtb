package com.example.testtaskmockvtb.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MockRequest {

    private String name;
    private int age;
    private MotherDTO mother;
    private List<String> children;
    private boolean married;
    private String dog;

}
