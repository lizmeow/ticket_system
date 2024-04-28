package com.ticketsystem.service.activity;

import com.ticketsystem.service.dynamodb.Ticket;
import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.dynamodb.User;
import com.ticketsystem.service.dynamodb.UserDao;
import com.ticketsystem.service.exceptions.UserIdNotFoundException;
import com.ticketsystem.service.models.requests.GetTicketsAssignedToUserIdRequest;
import com.ticketsystem.service.models.requests.GetTicketsCreatedByUserRequest;
import com.ticketsystem.service.models.results.GetTicketsAssignedToUserIdResult;
import com.ticketsystem.service.models.results.GetTicketsCreatedByUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetTicketsAssignedtoUserActivityTest {
    @Mock
    private UserDao userDao;

    @Mock
    private TicketDao ticketDao;

    private GetTicketsAssignedToUserActivity getTicketsAssignedToUserActivity;

    @BeforeEach
    private void setup() {
        initMocks(this);
        getTicketsAssignedToUserActivity = new GetTicketsAssignedToUserActivity(userDao, ticketDao);
    }

    @Test
    void handleRequest_UserIdNotFound_ThrowException() {
        // GIVEN
        GetTicketsAssignedToUserIdRequest request = new GetTicketsAssignedToUserIdRequest();
        request.setUserId("badUserId");
        when(userDao.getUser("badUserId")).thenReturn(null);

        // WHEN, THEN
        assertThrows(UserIdNotFoundException.class,
                () -> getTicketsAssignedToUserActivity.handleRequest(request, null));
    }

    @Test
    void handleRequest_validUserId_ReturnValidResult() {
        // GIVEN
        User user = new User();
        user.setId("userId");
        GetTicketsAssignedToUserIdRequest request = new GetTicketsAssignedToUserIdRequest();
        request.setUserId("userId");
        when(userDao.getUser("userId")).thenReturn(user);
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = new Ticket();
        ticket.setCreatedByUserId("userId");
        tickets.add(ticket);
        when(ticketDao.getTicketsAssignedToUserId("userId")).thenReturn(tickets);

        // WHEN
        GetTicketsAssignedToUserIdResult result = getTicketsAssignedToUserActivity.handleRequest(request, null);

        // THEN
        assertEquals(1, result.getTicketModelList().size());
        assertEquals("userId", result.getTicketModelList().get(0).getCreatedByUserId());
    }
}
