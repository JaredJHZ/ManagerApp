package com.jared.manager.repositories;

import com.jared.manager.entities.Departments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.concurrent.CompletableFuture;

@Repository
public class DepartmentRepository {


    private DynamoDbEnhancedAsyncClient enhancedAsyncClient;

    private DynamoDbAsyncTable<Departments> departamentsDynamoDbAsyncTable;

    @Autowired
    public DepartmentRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.enhancedAsyncClient = asyncClient;
        this.departamentsDynamoDbAsyncTable = enhancedAsyncClient.table
                (Departments.class.getSimpleName(), TableSchema.fromBean(Departments.class));
    }

    public CompletableFuture<Void> save(Departments department) {
        return departamentsDynamoDbAsyncTable.putItem(department);
    }

}
