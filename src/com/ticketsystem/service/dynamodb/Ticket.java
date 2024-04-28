package com.ticketsystem.service.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Tickets")
public class Ticket {
    private String ticketId;
    private String shortDescription;
    private String longDescription;
    private String creationDate;
    private String createdByUserId;
    private String assignedToUserId;
    private String state;
    private String resolutionText;

    @DynamoDBHashKey(attributeName = "ticketId")
    public String getTicketId() {return ticketId; }

    @DynamoDBAttribute(attributeName = "shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @DynamoDBAttribute(attributeName = "longDescription")
    public String getLongDescription() {
        return longDescription;
    }

    @DynamoDBAttribute(attributeName = "creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @DynamoDBAttribute(attributeName = "createdByUserId")
    public String getCreatedByUserId() {
        return createdByUserId;
    }

    @DynamoDBAttribute(attributeName = "assignedToUserId")
    public String getAssignedToUserId() {
        return assignedToUserId;
    }

    @DynamoDBAttribute(attributeName = "state")
    public String getState() {
        return state;
    }

    @DynamoDBAttribute(attributeName = "resolutionText")
    public String getResolutionText() {
        return resolutionText;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public void setAssignedToUserId(String assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setResolutionText(String resolutionText) {
        this.resolutionText = resolutionText;
    }
}
