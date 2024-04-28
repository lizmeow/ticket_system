package com.ticketsystem.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.converters.ModelConverter;
import com.ticketsystem.service.dynamodb.Ticket;
import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.dynamodb.User;
import com.ticketsystem.service.dynamodb.UserDao;
import com.ticketsystem.service.exceptions.UserIdNotFoundException;
import com.ticketsystem.service.models.TicketModel;
import com.ticketsystem.service.models.requests.GetTicketsCreatedByUserRequest;
import com.ticketsystem.service.models.results.GetTicketsCreatedByUserResult;

import java.util.List;

public class GetTicketsCreatedByUserActivity implements RequestHandler<GetTicketsCreatedByUserRequest, GetTicketsCreatedByUserResult> {
    private TicketDao ticketDao;
    private UserDao userDao;

    public GetTicketsCreatedByUserActivity(UserDao userDao, TicketDao ticketDao) {
        this.ticketDao = ticketDao;
        this.userDao = userDao;
    }

    @Override
    public GetTicketsCreatedByUserResult handleRequest(GetTicketsCreatedByUserRequest request, Context context) {
        // Unpack request
        String userId = request.getUserId();

        // Look up user id in user id table, if not found, throw a userIdNotFoundException
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new UserIdNotFoundException("User id " + userId + " is not found");
        }

        // Get all tickets created by the user Id in the tickets table in dynamo db
        List<Ticket> tickets = ticketDao.getTicketsCreatedByUserId(userId);

        // Convert List of tickets into a List<TicketModel>
        ModelConverter modelConverter = new ModelConverter();
        List<TicketModel> ticketModels = modelConverter.toTicketModelList(tickets);

        // Return a result object with List<TicketModel> as a field
        return GetTicketsCreatedByUserResult.builder()
                .withTicketModelList(ticketModels)
                .build();
    }
}
