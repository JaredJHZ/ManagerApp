package com.jared.manager.controllers;

import com.jared.manager.daos.DepartmentResponse;
import com.jared.manager.entities.Departments;
import com.jared.manager.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService service;
    @PostMapping("/save")
    public Mono<DepartmentResponse> saveDepartment(@RequestBody Departments depto) {
        return service.createNewDepartment(depto);
    }

    @GetMapping("/all")
    public Flux<DepartmentResponse> getAllDepartments() {
        return service.getAlldepartments();
    }

}
