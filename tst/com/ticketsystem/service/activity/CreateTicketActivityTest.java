package com.ticketsystem.service.activity;

import com.ticketsystem.service.dynamodb.TicketDao;
import com.ticketsystem.service.dynamodb.User;
import com.ticketsystem.service.dynamodb.UserDao;
import com.ticketsystem.service.exceptions.InvalidAttributeException;
import com.ticketsystem.service.exceptions.UserIdNotFoundException;
import com.ticketsystem.service.models.requests.CreateTicketRequest;
import com.ticketsystem.service.models.results.CreateTicketResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateTicketActivityTest {
    @Mock
    private UserDao userDao;

    @Mock
    private TicketDao ticketDao;

    private CreateTicketActivity createTicketActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createTicketActivity = new CreateTicketActivity(userDao, ticketDao);
    }

    @Test
    public void handleRequest_whenNullShortDescription_ThrowInvalidAttributeException() {
        // GIVEN
        CreateTicketRequest request = CreateTicketRequest.builder()
                                        .withShortDescription(null)
                                        .withLongDescription("long")
                                        .withAssignedToUserId("assignedId")
                                        .withCreatedByUserId("createdId")
                                        .build();
        // WHEN, THEN
        assertThrows(InvalidAttributeException.class, () -> {
            createTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenNullLongDescription_ThrowInvalidAttributeException() {
        // GIVEN
        CreateTicketRequest request = CreateTicketRequest.builder()
                .withShortDescription("short")
                .withLongDescription(null)
                .withAssignedToUserId("assignedId")
                .withCreatedByUserId("createdId")
                .build();
        // WHEN, THEN
        assertThrows(InvalidAttributeException.class, () -> {
            createTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenEmptyShortDescription_ThrowInvalidAttributeException() {
        // GIVEN
        CreateTicketRequest request = CreateTicketRequest.builder()
                .withShortDescription("")
                .withLongDescription("long")
                .withAssignedToUserId("assignedId")
                .withCreatedByUserId("createdId")
                .build();
        // WHEN, THEN
        assertThrows(InvalidAttributeException.class, () -> {
            createTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenEmptyLongDescription_ThrowInvalidAttributeException() {
        // GIVEN
        CreateTicketRequest request = CreateTicketRequest.builder()
                .withShortDescription("short")
                .withLongDescription("")
                .withAssignedToUserId("assignedId")
                .withCreatedByUserId("createdId")
                .build();
        // WHEN, THEN
        assertThrows(InvalidAttributeException.class, () -> {
            createTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenAssignedUserIdNotFound_ThrowUserIdNotFoundException() {
        // GIVEN
        User user = new User();
        user.setId("createdId");
        user.setName("name");
        CreateTicketRequest request = CreateTicketRequest.builder()
                .withShortDescription("short")
                .withLongDescription("long")
                .withAssignedToUserId("assignedId")
                .withCreatedByUserId("createdId")
                .build();
        when(userDao.getUser("createdId")).thenReturn(user);
        when(userDao.getUser("assignedId")).thenReturn(null);

        // WHEN, THEN
        assertThrows(UserIdNotFoundException.class, () -> {
            createTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenCreateByUserIdNotFound_ThrowUserIdNotFoundException() {
        // GIVEN
        User user = new User();
        user.setId("assignedId");
        user.setName("name");
        CreateTicketRequest request = CreateTicketRequest.builder()
                .withShortDescription("short")
                .withLongDescription("long")
                .withAssignedToUserId("assignedId")
                .withCreatedByUserId("createdId")
                .build();
        when(userDao.getUser("assignedId")).thenReturn(user);
        when(userDao.getUser("createdId")).thenReturn(null);

        // WHEN, THEN
        assertThrows(UserIdNotFoundException.class, () -> {
            createTicketActivity.handleRequest(request, null);
        });
    }

    @Test
    public void handleRequest_whenValidRequest_returnValidTicketResult() {
        // GIVEN
        User user = new User();
        user.setId("id");
        user.setName("name");
        when(userDao.getUser("id")).thenReturn(user);
        CreateTicketRequest request = CreateTicketRequest.builder()
                .withShortDescription("short")
                .withLongDescription("long")
                .withAssignedToUserId("id")
                .withCreatedByUserId("id")
                .build();


        // WHEN
        CreateTicketResult result = createTicketActivity.handleRequest(request, null);

        // THEN
        assert(!result.getTicket().getTicketId().isEmpty());
        assertEquals("id", result.getTicket().getAssignedToUserId());
        assertEquals("id", result.getTicket().getCreatedByUserId());
        assertEquals("long", result.getTicket().getLongDescription());
        assertEquals("short", result.getTicket().getShortDescription());
        assertEquals("N/A", result.getTicket().getResolutionText());
        assertEquals("OPEN", result.getTicket().getState());
    }
}
