package com.jared.manager;

import com.jared.manager.entities.Departments;
import com.jared.manager.entities.Leaders;
import com.jared.manager.repositories.LeadersRepository;
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

    @Autowired
    private LeadersRepository repo;

    @Override
    public void run(String... args) throws ExecutionException, InterruptedException {
//
//        this.createDepartmentsTable();
//        this.createLeadersTable();
//
//        for (int i = 0 ; i < 100; i++) {
//            System.out.println("---");
//        }
//        repo.get("hola");
    }


    private void createDepartmentsTable() {
        try{
            CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient.listTables();
            CompletableFuture<List<String>> listCompletableFuture = listTablesResponseCompletableFuture.thenApply(ListTablesResponse::tableNames);
            listCompletableFuture.thenAccept(tables -> {
                    if(!tables.contains(Departments.class.getSimpleName())) {
                        DynamoDbAsyncTable<Departments> departmentsTable = enhancedAsyncClient.table(
                                Departments.class.getSimpleName(),
                                TableSchema.fromBean(Departments.class));
                        departmentsTable.createTable();
                    } else {
                        System.out.printf("%d is alreday in the db", Departments.class.getSimpleName());
                    }
            });
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void createLeadersTable() {
        try {

            CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient.listTables();
            CompletableFuture<List<String>> listCompletableFuture =
                    listTablesResponseCompletableFuture.thenApply(ListTablesResponse::tableNames);
            listCompletableFuture.thenAccept(tables -> {

                if(!tables.contains(Leaders.class.getSimpleName())) {
                    try{

                        DynamoDbAsyncTable<Leaders> leadersTable = enhancedAsyncClient.table(
                                Leaders.class.getSimpleName(),
                                TableSchema.fromBean(Leaders.class));

                        leadersTable.createTable();
                    }catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    System.out.printf("%d is alreday in the db", Leaders.class.getSimpleName());
                }
            });
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
