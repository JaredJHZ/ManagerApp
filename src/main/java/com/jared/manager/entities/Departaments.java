package com.jared.manager.entities;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Departaments {

    private String departmentId;
    private String departmentName;
    private String leader;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("DepartmentID")
    public String getDepartmentId() {
        return departmentId;
    }

    @DynamoDbAttribute("DepartmentName")
    public String getDepartmentName() {
        return departmentName;
    }

    @DynamoDbAttribute("Leader")
    public String getLeader() {
        return leader;
    }

}
