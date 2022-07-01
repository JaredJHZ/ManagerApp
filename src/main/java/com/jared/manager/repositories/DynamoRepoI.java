package com.jared.manager.repositories;

import com.jared.manager.entities.Departments;
import com.jared.manager.entities.Leaders;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface DynamoRepoI<T> {
    CompletableFuture<Void> save(T object);
    CompletableFuture<Leaders> get(Integer key);

    PagePublisher<T> getAll();
}
