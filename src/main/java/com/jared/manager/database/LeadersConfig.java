package com.jared.manager.database;

import com.amazonaws.regions.Regions;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public class LeadersConfig extends DynamoDBConfig{

    @Override
    public DynamoDbAsyncClient getDynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(()-> AwsBasicCredentials.create(amazonAWSAccessKey,amazonAWSSecretKey))
                .build();
    }

    @Override
    public DynamoDbEnhancedAsyncClient getDynamoDBEnhancedAsyncClient() {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(getDynamoDbAsyncClient())
                .build();
    }
}
