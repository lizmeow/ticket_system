package com.ticketsystem.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.converters.ModelConverter;
import com.ticketsystem.service.dynamodb.User;
import com.ticketsystem.service.dynamodb.UserDao;
import com.ticketsystem.service.models.UserModel;
import com.ticketsystem.service.models.requests.GetUsersRequest;
import com.ticketsystem.service.models.results.GetUsersResult;

import java.util.ArrayList;
import java.util.List;

public class GetUsersActivity implements RequestHandler<GetUsersRequest, GetUsersResult> {
    private final UserDao userDao;

    public GetUsersActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public GetUsersResult handleRequest(final GetUsersRequest getUserRequest, Context context) {
        List<User> users = userDao.getUsers();

        // for each user, create a UserModel
        ModelConverter modelConverter = new ModelConverter();
        List<UserModel> userModels = new ArrayList<>();

        for (User user : users) {
            UserModel userModel = modelConverter.toUserModel(user);
            userModels.add(userModel);
        }

        return GetUsersResult.builder()
                .withUserList(userModels)
                .build();
    }
}
