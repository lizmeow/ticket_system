package com.ticketsystem.service.activity;

import com.ticketsystem.service.dynamodb.Ticket;
import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.exceptions.InvalidAttributeException;
import com.ticketsystem.service.exceptions.TicketIdNotFoundException;
import com.ticketsystem.service.exceptions.UserIdNotFoundException;
import com.ticketsystem.service.models.requests.GetTicketRequest;
import com.ticketsystem.service.models.results.GetTicketResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetTicketActivityTest {
    @Mock
    private TicketDao ticketDao;

    GetTicketActivity getTicketActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getTicketActivity = new GetTicketActivity(ticketDao);
    }

    @Test
    public void handleRequest_whenValidTicketId_ReturnValidResult() {
        // GIVEN
        GetTicketRequest request = GetTicketRequest.builder()
                                    .withTicketId("id")
                                    .build();
        Ticket ticket = new Ticket();
        ticket.setTicketId("id");
        when(ticketDao.getTicket("id")).thenReturn(ticket);

        // WHEN
        GetTicketResult result = getTicketActivity.handleRequest(request, null);

        // THEN
        assertEquals("id", result.getTicket().getTicketId());
    }

    @Test
    public void handleRequest_whenInvalidTicketId_ThrowTicketNotFoundException() {
        // GIVEN
        GetTicketRequest request = GetTicketRequest.builder()
                .withTicketId("id")
                .build();
        when(ticketDao.getTicket("id")).thenReturn(null);

        // WHEN
        assertThrows(TicketIdNotFoundException.class, () -> {
            getTicketActivity.handleRequest(request, null);
        });
    }
}
