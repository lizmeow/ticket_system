package com.ticketsystem.service.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketDao {
    private final DynamoDBMapper dynammoDBMapper;

    public TicketDao(DynamoDBMapper dynamoDBMapper) {
        this.dynammoDBMapper = dynamoDBMapper;
    }

    public void saveTicket(Ticket ticket) {
        this.dynammoDBMapper.save(ticket);
    }

    public Ticket getTicket(String ticketId) {
        return this.dynammoDBMapper.load(Ticket.class, ticketId);
    }

    // TODO
    //public List<Ticket> getTickets(String value, String field)

    public List<Ticket> getTicketsCreatedByUserId(String createdByUserId) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":createdByUserId", new AttributeValue().withS(createdByUserId));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("createdByUserId = :createdByUserId")
                .withExpressionAttributeValues(expressionAttributeValues);
        return this.dynammoDBMapper.scan(Ticket.class, scanExpression);
    }

    public List<Ticket> getTicketsAssignedToUserId(String assignedToUserId) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":assignedToUserId", new AttributeValue().withS(assignedToUserId));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("assignedToUserId = :assignedToUserId")
                .withExpressionAttributeValues(expressionAttributeValues);
        return this.dynammoDBMapper.scan(Ticket.class, scanExpression);
    }
}
