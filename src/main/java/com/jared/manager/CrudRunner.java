package com.jared.manager;

import com.jared.manager.entities.Departments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class CrudRunner implements CommandLineRunner {
    @Autowired
    private DynamoDbAsyncClient asyncClient;
    @Autowired
    private DynamoDbEnhancedAsyncClient enhancedAsyncClient;


    @Override
    public void run(String... args) throws ExecutionException, InterruptedException {

        CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient.listTables();

        CompletableFuture<List<String>> listCompletableFuture = listTablesResponseCompletableFuture.thenApply(ListTablesResponse::tableNames);

        listCompletableFuture.thenAccept(tables -> {
            try{
                if(!tables.contains(Departments.class.getSimpleName())) {
                    DynamoDbAsyncTable<Departments> departmentsTable = enhancedAsyncClient.table(
                            Departments.class.getSimpleName(),
                            TableSchema.fromBean(Departments.class));
                    departmentsTable.createTable();
                } else {
                    System.out.println("Table is already created");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        });
    }
}
