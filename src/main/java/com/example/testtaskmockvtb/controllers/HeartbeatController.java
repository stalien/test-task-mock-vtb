package com.example.testtaskmockvtb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatController {
    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
}
