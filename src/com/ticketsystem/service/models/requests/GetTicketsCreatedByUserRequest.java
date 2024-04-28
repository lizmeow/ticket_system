package com.ticketsystem.service.models.requests;

import java.util.Objects;

public class GetTicketsCreatedByUserRequest {
    private String createdByUserId;

    public String getUserId() {
        return createdByUserId;
    }

    public void setUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public GetTicketsCreatedByUserRequest() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetTicketsCreatedByUserRequest that = (GetTicketsCreatedByUserRequest) o;
        return Objects.equals(createdByUserId, that.createdByUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdByUserId);
    }

    @Override
    public String toString() {
        return "GetTicketsCreatedByUserRequest{" +
                "createdByUserId='" + createdByUserId+ '\'' +
                '}';
    }

   public GetTicketsCreatedByUserRequest(String userId) {
        this.createdByUserId = userId;
   }

   public GetTicketsCreatedByUserRequest(Builder builder) {
        this.createdByUserId = builder.createdByUserId;
   }

   public static Builder builder() {
        return new Builder();
   }

   public final static class Builder {
        private String createdByUserId;

        private Builder() {

        }
        public Builder withUserId(String userId) {
            this.createdByUserId = userId;
            return this;
        }
        public GetTicketsCreatedByUserRequest build() {
            return new GetTicketsCreatedByUserRequest(this);
        }
   }
}
