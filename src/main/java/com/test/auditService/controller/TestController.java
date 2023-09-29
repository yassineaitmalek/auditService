package com.test.auditService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.auditService.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/test")
@RequiredArgsConstructor
class TestController {

    private final TestService testService;

    @GetMapping("/hello")
    public String hello() {
        return testService.hello();

    }

    @GetMapping("/hello/{firstName}/{lastName}")
    public String hello2(@PathVariable String firstName, @PathVariable String lastName) {
        return testService.hello2(firstName, lastName);

    }

}
