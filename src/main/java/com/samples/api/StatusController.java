package com.samples.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class StatusController {
    @GetMapping(path = "/status", produces = TEXT_PLAIN_VALUE)
    public String status() {
        return "OK";
    }
}
