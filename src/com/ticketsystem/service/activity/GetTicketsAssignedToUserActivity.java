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
import com.ticketsystem.service.models.requests.GetTicketsAssignedToUserIdRequest;
import com.ticketsystem.service.models.results.GetTicketsAssignedToUserIdResult;
import com.ticketsystem.service.models.results.GetTicketsCreatedByUserResult;

import java.util.List;

public class GetTicketsAssignedToUserActivity implements RequestHandler<GetTicketsAssignedToUserIdRequest, GetTicketsAssignedToUserIdResult> {
    private TicketDao ticketDao;
    private UserDao userDao;

    public GetTicketsAssignedToUserActivity(UserDao userDao, TicketDao ticketDao) {
        this.ticketDao = ticketDao;
        this.userDao = userDao;
    }

    @Override
    public GetTicketsAssignedToUserIdResult handleRequest(GetTicketsAssignedToUserIdRequest request, Context context) {
        // unpack request object
        String userId = request.getUserId();

        // Check that user id exists in db, else throw exception
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new UserIdNotFoundException("User id " + userId + " not found.");
        }

        // Get a list of tickets assigned to this user id
        List<Ticket> tickets = ticketDao.getTicketsAssignedToUserId(userId);

        // Convert list of tickets to list of TicketModels
        ModelConverter modelConverter = new ModelConverter();
        List<TicketModel> ticketModels = modelConverter.toTicketModelList(tickets);

        // return a result with a field being the list of TIcketModels
        return GetTicketsAssignedToUserIdResult.builder()
                .withTicketModelList(ticketModels)
                .build();
    }


}
