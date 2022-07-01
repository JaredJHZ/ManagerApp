package com.jared.manager.services;

import com.jared.manager.daos.DepartmentResponse;
import com.jared.manager.daos.LeaderResponse;
import com.jared.manager.entities.Departments;
import com.jared.manager.entities.Leaders;
import com.jared.manager.repositories.DepartmentRepository;
import com.jared.manager.repositories.LeadersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
public class LeaderService {
    @Autowired
    LeadersRepository repo;

    public Mono<LeaderResponse> create(Leaders leader) {
        return Mono.fromFuture(
                repo.save(leader)
        ).thenReturn(
            setResponse(leader)
        ).onErrorReturn(null);
    }

    public Mono<LeaderResponse> get(Integer id) throws ExecutionException, InterruptedException {
        var obj = repo.get(id).get();
        return Mono.fromFuture(
                repo.get(id)
        ).thenReturn(
                setResponse(obj)
        ).onErrorReturn(null);
    }

    private LeaderResponse setResponse(Leaders leader) {
        var obj = new LeaderResponse();
        obj.setLeadersName(leader.getLeaderName());
        return obj;
    }
}
