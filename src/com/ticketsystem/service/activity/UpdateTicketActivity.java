package com.ticketsystem.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.converters.ModelConverter;
import com.ticketsystem.service.dynamodb.Ticket;
import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.enums.State;
import com.ticketsystem.service.exceptions.InvalidAttributeException;
import com.ticketsystem.service.exceptions.TicketIdNotFoundException;
import com.ticketsystem.service.models.TicketModel;
import com.ticketsystem.service.models.requests.UpdateTicketRequest;
import com.ticketsystem.service.models.results.UpdateTicketResult;

public class UpdateTicketActivity implements RequestHandler<UpdateTicketRequest, UpdateTicketResult> {
    private TicketDao ticketDao;

    public UpdateTicketActivity(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public UpdateTicketResult handleRequest(UpdateTicketRequest updateTicketRequest, Context context) {
        // Unpack the request object
        String ticketId = updateTicketRequest.getTicketId();
        State state = updateTicketRequest.getState();
        String userId = updateTicketRequest.getUserId();
        String resolutionText = updateTicketRequest.getResolutionText();

        // Check that the Resolution text is not null or empty, else throw error
        if (resolutionText == null || resolutionText.isEmpty()) {
            throw new InvalidAttributeException("Invalid Resolution Text");
        }

        // Check that the state of the request object is CLOSED, else throw error
        if (state != State.CLOSED) {
            throw new InvalidAttributeException("To update a ticket, the new state must be " + State.CLOSED);
        }

        // Check that the ticketId exists, if not throw error
        Ticket ticket = ticketDao.getTicket(ticketId);
        if (ticket == null) {
            throw new TicketIdNotFoundException("Ticket id " + ticketId + " is not found");
        }

        // Check that the userId in the request matches the assignedTo id in the ticket in DynamoDB,
        // if it doesn't match, throw error
        if (!ticket.getAssignedToUserId().equals(userId)) {
            throw new InvalidAttributeException("Only user id " + ticket.getAssignedToUserId() + " can update ticket id " + ticketId);
        }

        // Set the ticket resolution field to the new resolution field
        ticket.setResolutionText(resolutionText);

        // Set the state to CLOSED
        ticket.setState(String.valueOf(state));

        // save ticket to the DynamoDB
        ticketDao.saveTicket(ticket);

        // Convert ticket to ticket model object to convert data from dynamoDB to a form for client
        ModelConverter modelConverter = new ModelConverter();
        TicketModel ticketModel = modelConverter.toTicketModel(ticket);

        // return an UpdateTicketResult with ticket model as a field
        return UpdateTicketResult.builder()
                .withTicket(ticketModel)
                .build();
    }

}
