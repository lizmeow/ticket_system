package com.ticketsystem.service.activity;

import com.ticketsystem.service.dynamodb.User;
import com.ticketsystem.service.dynamodb.UserDao;
import com.ticketsystem.service.models.requests.GetUsersRequest;
import com.ticketsystem.service.models.results.GetUsersResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetUsersActivityTest {
    @Mock
    private UserDao userDao;

    private GetUsersActivity getUsersActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getUsersActivity = new GetUsersActivity(userDao);
    }

    @Test
    public void handleRequest_returnGetEmptyUsersResult() {
        // GIVEN
        List<User> users = new ArrayList<>();
        when(userDao.getUsers()).thenReturn(users);
        GetUsersRequest request = new GetUsersRequest();

        // WHEN
        GetUsersResult result = getUsersActivity.handleRequest(request, null);

        // THEN
        assertEquals(0, result.getUserList().size());
    }

    @Test
    public void handleRequest_returnGetNonEmptyUsersResult() {
        // GIVEN
        User user = new User();
        user.setId("id");
        user.setName("name");
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userDao.getUsers()).thenReturn(users);
        GetUsersRequest request = new GetUsersRequest();

        // WHEN
        GetUsersResult result = getUsersActivity.handleRequest(request, null);

        // THEN
        assertEquals(1, result.getUserList().size());
        assertEquals("id", result.getUserList().get(0).getId());
        assertEquals("name", result.getUserList().get(0).getName());
    }

}
