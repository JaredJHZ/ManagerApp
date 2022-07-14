package com.jared.manager.controllers;

import com.jared.manager.daos.DepartmentResponse;
import com.jared.manager.entities.Departments;
import com.jared.manager.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService service;
    @PostMapping("/save")
    public Mono<DepartmentResponse> saveDepartment(@RequestBody Departments depto) {
        try {
            return service.createNewDepartment(depto);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public Flux<DepartmentResponse> getAllDepartments() {
        return service.getAlldepartments();
    }

}
