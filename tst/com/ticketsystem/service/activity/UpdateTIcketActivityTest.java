package com.ticketsystem.service.activity;

import com.ticketsystem.service.dynamodb.Ticket;
import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.enums.State;
import com.ticketsystem.service.exceptions.InvalidAttributeException;
import com.ticketsystem.service.exceptions.TicketIdNotFoundException;
import com.ticketsystem.service.models.requests.UpdateTicketRequest;
import com.ticketsystem.service.models.results.UpdateTicketResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UpdateTIcketActivityTest {
    @Mock
    TicketDao ticketDao;

    UpdateTicketActivity updateTicketActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        updateTicketActivity = new UpdateTicketActivity(ticketDao);

    }

    @Test
    public void handleRequest_whenTicketIdNotFound_ThrowExecption() {
        // GIVEN
        UpdateTicketRequest request = UpdateTicketRequest.builder()
                .withTicketId("badTicketId")
                .withResolutionText("resolved")
                .withState(State.CLOSED)
                .withUserId("userId")
                .build();
        when(ticketDao.getTicket("badTicketId")).thenReturn(null);

        // WHEN, THEN
        assertThrows(TicketIdNotFoundException.class, () -> {
            updateTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenNullResolutionText_ThrowException() {
        // Given
        UpdateTicketRequest request = UpdateTicketRequest.builder()
                .withTicketId("TicketId")
                .withResolutionText(null)
                .withState(State.CLOSED)
                .withUserId("userId")
                .build();

        // WHEN, THEN
        assertThrows(InvalidAttributeException.class, () -> {
            updateTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenEmptyResolutionText_ThrowException() {
        // Given
        UpdateTicketRequest request = UpdateTicketRequest.builder()
                .withTicketId("TicketId")
                .withResolutionText("")
                .withState(State.CLOSED)
                .withUserId("userId")
                .build();

        // WHEN, THEN
        assertThrows(InvalidAttributeException.class, () -> {
            updateTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenInvalidState_ThrowException() {
        // Given
        UpdateTicketRequest request = UpdateTicketRequest.builder()
                .withTicketId("TicketId")
                .withResolutionText("resolved")
                .withState(State.OPEN)
                .withUserId("userId")
                .build();

        // WHEN, THEN
        assertThrows(InvalidAttributeException.class, () -> {
            updateTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenInvalidUserId_ThrowException() {
        // Given
        UpdateTicketRequest request = UpdateTicketRequest.builder()
                .withTicketId("TicketId")
                .withResolutionText("resolved")
                .withState(State.CLOSED)
                .withUserId("userId")
                .build();
        Ticket ticket = new Ticket();
        ticket.setAssignedToUserId("differentUserId");
        when(ticketDao.getTicket("TicketId")).thenReturn(ticket);

        // WHEN, THEN
        assertThrows(InvalidAttributeException.class, () -> {
            updateTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenValidRequest_ReturnValidResult() {
        // Given
        UpdateTicketRequest request = UpdateTicketRequest.builder()
                .withTicketId("ticketId")
                .withResolutionText("resolved")
                .withState(State.CLOSED)
                .withUserId("userId")
                .build();
        Ticket ticket = new Ticket();
        ticket.setAssignedToUserId("userId");
        ticket.setState(String.valueOf(State.CLOSED));
        ticket.setResolutionText("resolved");
        ticket.setTicketId("ticketId");
        when(ticketDao.getTicket("ticketId")).thenReturn(ticket);

        // When
        UpdateTicketResult result = updateTicketActivity.handleRequest(request, null);

        // THEN
        assertEquals("ticketId", result.getTicket().getTicketId());
        assertEquals("resolved", result.getTicket().getResolutionText());
        assertEquals("userId", result.getTicket().getAssignedToUserId());
        assertEquals(String.valueOf(State.CLOSED), result.getTicket().getState());

    }
}
