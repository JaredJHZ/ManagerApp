package com.jared.manager.database;

import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public abstract class DynamoDBConfig {
    @Value("${amazon.aws.accesskey}")
    protected String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    protected String amazonAWSSecretKey;

    public abstract DynamoDbAsyncClient getDynamoDbAsyncClient();
    public abstract DynamoDbEnhancedAsyncClient getDynamoDBEnhancedAsyncClient();



}
