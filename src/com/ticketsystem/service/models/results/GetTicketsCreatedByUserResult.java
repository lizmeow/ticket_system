package com.ticketsystem.service.models.results;

import com.ticketsystem.service.models.TicketModel;

import java.util.List;

public class GetTicketsCreatedByUserResult {
    private List<TicketModel> ticketModelList;
    public GetTicketsCreatedByUserResult(Builder builder) {
        this.ticketModelList = builder.ticketModelList;
    }

    public List<TicketModel> getTicketModelList() {
        return ticketModelList;
    }

    public void setTicketModelList(List<TicketModel> ticketModelList) {
        this.ticketModelList = ticketModelList;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private List<TicketModel> ticketModelList;
        public Builder withTicketModelList(List<TicketModel> ticketModelList) {
            this.ticketModelList = ticketModelList;
            return this;
        }
        public GetTicketsCreatedByUserResult build() {
            return new GetTicketsCreatedByUserResult(this);
        }
    }
}
