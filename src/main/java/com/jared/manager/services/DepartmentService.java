package com.jared.manager.services;

import com.jared.manager.entities.Departments;
import com.jared.manager.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository repo;

    public Mono<String> createNewDepartment(Departments depto) {
        return Mono.fromFuture(repo.save(depto))
                .thenReturn("ok")
                .onErrorReturn("bad");
    }
}
