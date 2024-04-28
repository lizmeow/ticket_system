package com.ticketsystem.service.dependency;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.ticketsystem.service.activity.*;
import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.dynamodb.UserDao;
import com.ticketsystem.service.lambda.GetTicketsAssignedToUserActivityProvider;
import com.ticketsystem.service.lambda.GetTicketsCreatedByUserActivityProvider;

public class App {
    private DynamoDBMapper dynamoDBMapper;

    public GetUsersActivity provideGetUsersActivity() {
        return new GetUsersActivity(provideUserDao());
    }

    public CreateTicketActivity provideCreateTicketActivity() {
        return new CreateTicketActivity(provideUserDao(), provideTicketDao());
    }

    public GetTicketActivity provideGetTicketActivity() {
        return new GetTicketActivity(provideTicketDao());
    }

    public UpdateTicketActivity provideUpdateTicketActivity() {
        return new UpdateTicketActivity(provideTicketDao());
    }

    public GetTicketsCreatedByUserActivity provideGetTicketsCreatedByUserActivity() {
        return new GetTicketsCreatedByUserActivity(provideUserDao(), provideTicketDao());
    }

    public GetTicketsAssignedToUserActivity provideGetTicketsAssignedToUserActivity() {
        return new GetTicketsAssignedToUserActivity(provideUserDao(), provideTicketDao());
    }

    private UserDao provideUserDao() {
        return new UserDao(provideDynamoDBMapper());
    }

    private TicketDao provideTicketDao() {
        return new TicketDao(provideDynamoDBMapper());
    }

    private DynamoDBMapper provideDynamoDBMapper() {
        if (dynamoDBMapper == null) {
            AmazonDynamoDB amazonDynamoDB = (AmazonDynamoDB)((AmazonDynamoDBClientBuilder)((AmazonDynamoDBClientBuilder)AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())).withRegion(Regions.US_WEST_2)).build();
            dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        }
        return dynamoDBMapper;
    }
}
