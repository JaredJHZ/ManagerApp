package com.jared.manager.repositories;

import com.jared.manager.entities.Leaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;

@Repository
public class LeadersRepository implements DynamoRepoI<Leaders> {

    @Autowired
    private DynamoDbEnhancedAsyncClient enhancedAsyncClient;

    private DynamoDbAsyncTable<Leaders> leadersDynamoDbAsyncTable;

    LeadersRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.enhancedAsyncClient = asyncClient;
        this.leadersDynamoDbAsyncTable = enhancedAsyncClient.table
                (Leaders.class.getSimpleName(), TableSchema.fromBean(Leaders.class));
    }


    @Override
    public CompletableFuture<Void> save(Leaders leader) {
        return leadersDynamoDbAsyncTable.putItem(leader);
    }

    @Override
    public CompletableFuture<Leaders> get(Integer key) {
        return leadersDynamoDbAsyncTable.getItem(getKeyBuild(key));
    }

    @Override
    public PagePublisher<Leaders> getAll() {
        return leadersDynamoDbAsyncTable.scan();
    }

    private Key getKeyBuild(Integer key) {
        return Key.builder().partitionValue(key).build();
    }

}
