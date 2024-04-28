package com.ticketsystem.service.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

import java.util.List;

public class UserDao {
    private final DynamoDBMapper dynammoDBMapper;

    public UserDao(DynamoDBMapper dynamoDBMapper) {
        this.dynammoDBMapper = dynamoDBMapper;
    }

    public List<User> getUsers() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<User> scanResult = dynammoDBMapper.scan(User.class, scanExpression);
        return scanResult;
    }

    public User getUser(String id) {
        return this.dynammoDBMapper.load(User.class, id);
    }

}
