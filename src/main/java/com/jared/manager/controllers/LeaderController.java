package com.jared.manager.controllers;

import com.jared.manager.daos.LeaderResponse;
import com.jared.manager.entities.Leaders;
import com.jared.manager.services.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("leaders")
public class LeaderController {

    @Autowired
    LeaderService service;

    @PostMapping("/save")
    public Mono<LeaderResponse> saveLeader(@RequestBody Leaders leader) {
        return service.create(leader);
    }

    @GetMapping("/{id}")
    public Mono<LeaderResponse> getLeader(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        return service.get(id);
    }

}
