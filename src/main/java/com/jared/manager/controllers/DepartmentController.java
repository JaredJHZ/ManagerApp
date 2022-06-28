package com.jared.manager.controllers;

import com.jared.manager.entities.Departments;
import com.jared.manager.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService service;
    @PostMapping("/save")
    public Mono<String> saveDepartment(@RequestBody Departments depto) {
        return service.createNewDepartment(depto);
    }

}
