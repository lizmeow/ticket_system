package com.ticketsystem.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.dependency.App;
import com.ticketsystem.service.models.requests.GetUsersRequest;
import com.ticketsystem.service.models.results.GetUsersResult;

public class GetUsersActivityProvider implements RequestHandler<GetUsersRequest, GetUsersResult> {
    private static App app;

    public GetUsersActivityProvider() {

    }

    @Override
    public GetUsersResult handleRequest(final GetUsersRequest getUsersRequest, Context context) {
        return getApp().provideGetUsersActivity().handleRequest(getUsersRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

}
