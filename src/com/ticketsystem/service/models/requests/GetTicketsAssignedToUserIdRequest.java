package com.ticketsystem.service.models.requests;

import java.util.Objects;

public class GetTicketsAssignedToUserIdRequest {
    private String assignedToUserId;

    public String getUserId() {
        return assignedToUserId;
    }

    public void setUserId(String assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public GetTicketsAssignedToUserIdRequest() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetTicketsAssignedToUserIdRequest that = (GetTicketsAssignedToUserIdRequest) o;
        return Objects.equals(assignedToUserId, that.assignedToUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignedToUserId);
    }

    @Override
    public String toString() {
        return "GetTicketsAssignedToUserIdRequest{" +
                "assignedToUserId='" + assignedToUserId + '\'' +
                '}';
    }

    public GetTicketsAssignedToUserIdRequest(String userId) {
        this.assignedToUserId = userId;
    }

    public GetTicketsAssignedToUserIdRequest(Builder builder) {
        this.assignedToUserId = builder.assignedToUserId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {
        private String assignedToUserId;

        private Builder() {

        }
        public Builder withUserId(String userId) {
            this.assignedToUserId = userId;
            return this;
        }
        public GetTicketsAssignedToUserIdRequest build() {
            return new GetTicketsAssignedToUserIdRequest(this);
        }
    }
}
