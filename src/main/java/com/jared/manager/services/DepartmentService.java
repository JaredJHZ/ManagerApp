package com.jared.manager.services;

import com.jared.manager.daos.DepartmentResponse;
import com.jared.manager.entities.Departments;
import com.jared.manager.repositories.DepartmentRepository;
import com.jared.manager.repositories.LeadersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository repo;

    @Autowired
    LeadersRepository leader;

    public Mono<DepartmentResponse> createNewDepartment(Departments depto) throws ExecutionException, InterruptedException {

        var leaderExist = leader.get(depto.getLeader()).get();

        System.out.println("JOJOJOJOJO");
        System.out.println(leaderExist);


        return Mono.fromFuture(repo.save(depto))
                .thenReturn(setResponse(depto))
                .onErrorReturn(null);
    }

    public Flux<DepartmentResponse> getAlldepartments() {
        return Flux.from(
                repo.getAll()
                        .items()
                        .map(
                                departments -> setResponse(departments)
                        )
                )
                .onErrorReturn(
                        null
                );
    }

    private DepartmentResponse setResponse(Departments dep) {
        var depto = new DepartmentResponse();
        depto.setLeader(dep.getLeader());
        depto.setName(dep.getDepartmentName());
        return depto;
    }


}
