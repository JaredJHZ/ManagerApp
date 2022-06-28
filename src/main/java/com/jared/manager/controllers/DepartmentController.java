package com.jared.manager.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @PostMapping("/save")
    public Mono<String> saveDepartment() {
        return Mono.just("xd");
    }

}
