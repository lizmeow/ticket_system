package com.ticketsystem.service.models.requests;

import java.util.Objects;

public class GetTicketRequest {

    private String ticketId;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public GetTicketRequest() {

    }

    public GetTicketRequest(Builder builder) {
        this.ticketId = builder.ticketId;
    }

    public GetTicketRequest(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetTicketRequest that = (GetTicketRequest) o;
        return Objects.equals(ticketId, that.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    @Override
    public String toString() {
        return "GetTicketRequest{" +
                "ticketId='" + ticketId + '\'' +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String ticketId;

        private Builder() {
        }

        public Builder withTicketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public GetTicketRequest build() { return new GetTicketRequest(this); }
    }


}
