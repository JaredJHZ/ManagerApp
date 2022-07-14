package com.jared.manager.repositories;

import com.amazonaws.services.dynamodbv2.document.Index;
import com.jared.manager.entities.Leaders;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    public CompletableFuture<Leaders> get(String leaderName) throws ExecutionException, InterruptedException {
        var secIndex = enhancedAsyncClient.table(Leaders.class.getSimpleName(), TableSchema.fromBean(Leaders.class))
                .index("name-index");

        AttributeValue att = AttributeValue.builder().s(leaderName).build();

        Map<String, AttributeValue> expressionValues = new HashMap<>();

        expressionValues.put(":value", att);

        Expression expression = Expression.builder()
                .expression("name = :value")
                .expressionValues(expressionValues)
                .build();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(leaderName).build());

        var dbQuery = QueryEnhancedRequest.builder()
                .limit(3)
                .queryConditional(queryConditional)
                .build();

        var subscription = secIndex.query(dbQuery);

        CompletableFuture<Leaders> cf = new CompletableFuture<>();



        var future = subscription.subscribe((r) -> {

            if(r.items().isEmpty()){
                cf.cancel(true);
            }

            r.items().forEach(cf::complete);

        });


        return cf;

    }
}
