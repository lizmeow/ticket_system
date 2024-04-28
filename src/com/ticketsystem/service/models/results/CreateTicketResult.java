package com.ticketsystem.service.models.results;

import com.ticketsystem.service.models.TicketModel;

public class CreateTicketResult {
    private TicketModel ticket;

    public CreateTicketResult(Builder builder) {
        this.ticket = builder.ticket;
    }

    public TicketModel getTicket() {
        return ticket;
    }

    public void setTicket(TicketModel ticketModel) {
        this.ticket = ticketModel;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private TicketModel ticket;

        public Builder withTicket(TicketModel ticket) {
            this.ticket = ticket;
            return this;
        }

        public CreateTicketResult build() {return new CreateTicketResult(this);}
    }
}
