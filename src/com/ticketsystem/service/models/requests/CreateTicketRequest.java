package com.ticketsystem.service.models.requests;

import java.util.Objects;

public class CreateTicketRequest {
    private String createdByUserId;
    private String shortDescription;
    private String longDescription;
    private String assignedToUserId;

    public CreateTicketRequest() {

    }

    public CreateTicketRequest(String createdByUserId,
                               String shortDescription,
                               String longDescription,
                               String assignedToUserId) {
        this.createdByUserId = createdByUserId;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.assignedToUserId = assignedToUserId;
    }

    public CreateTicketRequest(Builder builder) {
        this.createdByUserId = builder.createdByUserId;
        this.shortDescription = builder.shortDescription;
        this.longDescription = builder.longDescription;
        this.assignedToUserId = builder.assignedToUserId;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getAssignedToUserId() {
        return assignedToUserId;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setAssignedToUserId(String assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTicketRequest that = (CreateTicketRequest) o;
        return Objects.equals(createdByUserId, that.createdByUserId) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(longDescription, that.longDescription) && Objects.equals(assignedToUserId, that.assignedToUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdByUserId, shortDescription, longDescription, assignedToUserId);
    }

    @Override
    public String toString() {
        return "CreateTicketRequest{" +
                "createdByUserId='" + createdByUserId + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", assignedToUserId='" + assignedToUserId + '\'' +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String createdByUserId;
        private String shortDescription;
        private String longDescription;
        private String assignedToUserId;

        private Builder() {

        }

        public Builder withCreatedByUserId(String createdByUserId) {
            this.createdByUserId = createdByUserId;
            return this;
        }

        public Builder withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder withLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        public Builder withAssignedToUserId(String assignedToUserId) {
            this.assignedToUserId = assignedToUserId;
            return this;
        }

        public CreateTicketRequest build() { return new CreateTicketRequest(this); }
    }


}
