package com.ticketsystem.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.converters.ModelConverter;
import com.ticketsystem.service.dynamodb.Ticket;
import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.exceptions.TicketIdNotFoundException;
import com.ticketsystem.service.models.TicketModel;
import com.ticketsystem.service.models.requests.GetTicketRequest;
import com.ticketsystem.service.models.results.GetTicketResult;

public class GetTicketActivity implements RequestHandler<GetTicketRequest, GetTicketResult> {
    private final TicketDao ticketDao;

    public GetTicketActivity(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public GetTicketResult handleRequest(final GetTicketRequest getTicketRequest,
                                            Context context) {
        // Unpack ticketRequest to get the ticket id
        String ticketId = getTicketRequest.getTicketId();

        // look up ticket id in Dynamo DB
        Ticket ticket = ticketDao.getTicket(ticketId);

        // If it doesn't exist, throw a TicketIdNotFoundEException
        if (ticket == null) {
            throw new TicketIdNotFoundException("Ticket id " + ticketId + " not found.");
        }

        // Convert dynamo DB results into model object
        ModelConverter modelConverter = new ModelConverter();
        TicketModel ticketModel = modelConverter.toTicketModel(ticket);

        // Return a result object with model object as a field
        return GetTicketResult.builder()
                .withTicket(ticketModel)
                .build();
    }
}
