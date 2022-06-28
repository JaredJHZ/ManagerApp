package com.jared.manager.entities;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Departments {

    private Integer departmentId;
    private String departmentName;
    private String leader;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("DepartmentID")
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer id){
        this.departmentId = id;
    }

    @DynamoDbAttribute("DepartmentName")
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }

    @DynamoDbAttribute("Leader")
    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader){
        this.leader = leader;
    }

}
