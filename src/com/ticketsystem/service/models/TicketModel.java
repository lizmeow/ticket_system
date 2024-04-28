package com.ticketsystem.service.models;

import java.util.List;
import java.util.Objects;

public class TicketModel {
    private String ticketId;
    private String shortDescription;
    private String longDescription;
    private String creationDate;
    private String createdByUserId;
    private String assignedToUserId;
    private String state;
    private String resolutionText;

    public TicketModel(Builder builder) {
        this.ticketId = builder.ticketId;
        this.shortDescription = builder.shortDescription;
        this.longDescription = builder.longDescription;
        this.creationDate = builder.creationDate;
        this.createdByUserId = builder.createdByUserId;
        this.assignedToUserId = builder.assignedToUserId;
        this.state = builder.state;
        this.resolutionText = builder.resolutionText;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public String getAssignedToUserId() {
        return assignedToUserId;
    }

    public String getState() {
        return state;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketModel that = (TicketModel) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(longDescription, that.longDescription) && Objects.equals(creationDate, that.creationDate) && Objects.equals(createdByUserId, that.createdByUserId) && Objects.equals(assignedToUserId, that.assignedToUserId) && Objects.equals(state, that.state) && Objects.equals(resolutionText, that.resolutionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, shortDescription, longDescription, creationDate, createdByUserId, assignedToUserId, state, resolutionText);
    }

    @Override
    public String toString() {
        return "TicketModel{" +
                "ticketId='" + ticketId + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", createdByUserId='" + createdByUserId + '\'' +
                ", assignedToUserId='" + assignedToUserId + '\'' +
                ", state='" + state + '\'' +
                ", resolutionText='" + resolutionText + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String ticketId;
        private String shortDescription;
        private String longDescription;
        private String creationDate;
        private String createdByUserId;
        private String assignedToUserId;
        private String state;
        private String resolutionText;

        public Builder withTicketId(String ticketId) {
            this.ticketId = ticketId;
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

        public Builder withCreationDate(String creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder withCreatedByUserId(String createdByUserId) {
            this.createdByUserId = createdByUserId;
            return this;
        }

        public Builder withAssignedToUserId(String assignedToUserId) {
            this.assignedToUserId = assignedToUserId;
            return this;
        }

        public Builder withState(String state) {
            this.state = state;
            return this;
        }

        public Builder withResolutionText(String resolutionText) {
            this.resolutionText = resolutionText;
            return this;
        }

        public TicketModel build() {return new TicketModel(this);}
    }


}
