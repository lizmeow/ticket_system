package com.ticketsystem.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.converters.ModelConverter;
import com.ticketsystem.service.dynamodb.Ticket;
import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.dynamodb.User;
import com.ticketsystem.service.dynamodb.UserDao;
import com.ticketsystem.service.enums.State;
import com.ticketsystem.service.exceptions.InvalidAttributeException;
import com.ticketsystem.service.exceptions.UserIdNotFoundException;
import com.ticketsystem.service.models.TicketModel;
import com.ticketsystem.service.models.requests.CreateTicketRequest;
import com.ticketsystem.service.models.results.CreateTicketResult;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.ZoneId;

public class CreateTicketActivity implements RequestHandler<CreateTicketRequest, CreateTicketResult> {
    private final UserDao userDao;
    private final TicketDao ticketDao;
    private static final int TICKET_ID_LENGTH = 5;
    private static final String DEFAULT_RESOLUTION_TEXT = "N/A";
    private static final String TIME_ZONE = "America/New_York";

    public CreateTicketActivity(UserDao userDao, TicketDao ticketDao) {
        this.userDao = userDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public CreateTicketResult handleRequest(final CreateTicketRequest createTicketRequest,
                                            Context context) {

        // Unpack Request
        String createdByUserId = createTicketRequest.getCreatedByUserId();
        String assignedToUserId = createTicketRequest.getAssignedToUserId();
        String longDescription = createTicketRequest.getLongDescription();
        String shortDescription = createTicketRequest.getShortDescription();

        // Check for valid short description
        if (shortDescription == null || shortDescription.length() == 0) {
            throw new InvalidAttributeException("Invalid short description");
        }

        // Check for valid long description
        if (longDescription == null || longDescription.length() == 0) {
            throw new InvalidAttributeException("Invalid long description");
        }

        // Check to make sure assigned to user id exists
        User retAssignedtoUser = this.userDao.getUser(assignedToUserId);
        if (retAssignedtoUser == null) {
            throw new UserIdNotFoundException("The user you assigned this ticket to is invalid.");
        }

        // Check to make sure created by user id exists
        User retCreatedByUser = this.userDao.getUser(createdByUserId);
        if (retCreatedByUser == null) {
            throw new UserIdNotFoundException("The user trying to make the ticket is invalid.");
        }

        // Create a ticket object
        Ticket ticket = new Ticket();
        ticket.setCreatedByUserId(createdByUserId);
        ticket.setAssignedToUserId(assignedToUserId);
        ticket.setLongDescription(longDescription);
        ticket.setShortDescription(shortDescription);
        ticket.setState(String.valueOf(State.OPEN));
        ticket.setResolutionText(DEFAULT_RESOLUTION_TEXT);

        // Generate ticket id
        String ticketId = RandomStringUtils.randomAlphanumeric(TICKET_ID_LENGTH);
        ticket.setTicketId(ticketId);


        // Get the current date as a string
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        LocalDate today = LocalDate.now( zoneId );
        String creationDate = today.toString();
        ticket.setCreationDate(creationDate);

        // save to database
        ticketDao.saveTicket(ticket);

        // transform Ticket object into a TicketModel object
        ModelConverter modelConverter = new ModelConverter();
        TicketModel ticketModel = modelConverter.toTicketModel(ticket);

        return CreateTicketResult.builder()
                .withTicket(ticketModel)
                .build();
    }
}
