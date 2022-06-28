package com.jared.manager.repositories;

import com.jared.manager.entities.Departaments;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.concurrent.CompletableFuture;

@Repository
public class DepartmentRepository {

    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<Departaments> departamentsDynamoDbAsyncTable;

    public DepartmentRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.enhancedAsyncClient = asyncClient;
        this.departamentsDynamoDbAsyncTable = enhancedAsyncClient.table
                (Departaments.class.getSimpleName(), TableSchema.fromBean(Departaments.class));
    }

    public CompletableFuture<Void> save(Departaments department) {
        return departamentsDynamoDbAsyncTable.putItem(department);
    }

}
