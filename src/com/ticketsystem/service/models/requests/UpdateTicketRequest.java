package com.ticketsystem.service.models.requests;

import com.ticketsystem.service.enums.State;

import java.util.Objects;

public class UpdateTicketRequest {
    private String ticketId;
    private String userId;
    private String resolutionText;
    private State state;

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public String getResolutionText() {
        return resolutionText;
    }

    public State getState() {
        return state;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setResolutionText(String resolutionText) {
        this.resolutionText = resolutionText;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateTicketRequest that = (UpdateTicketRequest) o;
        return Objects.equals(ticketId, that.ticketId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(resolutionText, that.resolutionText) &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, userId, resolutionText, state);
    }

    @Override
    public String toString() {
        return "UpdateTicketRequest{" +
                "ticketId='" + ticketId + '\'' +
                ", userId='" + userId + '\'' +
                ", resolutionText='" + resolutionText + '\'' +
                ", state=" + state +
                '}';
    }

    public UpdateTicketRequest() {

    }

    public UpdateTicketRequest(Builder builder) {
        this.ticketId = builder.ticketId;
        this.resolutionText = builder.resolutionText;
        this.state = builder.state;
        this.userId = builder.userId;
    }

    public UpdateTicketRequest(String ticketId,
                               String userId,
                               String resolutionText,
                               State state) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.resolutionText = resolutionText;
        this.state = state;
    }

    public static Builder builder() {return new Builder();}

    public final static class Builder {
        private String ticketId;
        private String userId;
        private String resolutionText;
        private State state;

        private Builder() {

        }

        public Builder withTicketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withResolutionText(String resolutionText) {
            this.resolutionText = resolutionText;
            return this;
        }

        public Builder withState(State state) {
            this.state = state;
            return this;
        }

        public UpdateTicketRequest build() {
            return new UpdateTicketRequest(this);
        }
    }
}
