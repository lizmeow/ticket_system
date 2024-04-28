package com.ticketsystem.service.lambda;

import com.amazonaws.Request;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.dependency.App;
import com.ticketsystem.service.models.requests.GetTicketsAssignedToUserIdRequest;
import com.ticketsystem.service.models.results.GetTicketsAssignedToUserIdResult;

public class GetTicketsAssignedToUserActivityProvider implements RequestHandler<GetTicketsAssignedToUserIdRequest, GetTicketsAssignedToUserIdResult> {
    private static App app;

    public GetTicketsAssignedToUserActivityProvider() {

    }

    @Override
    public GetTicketsAssignedToUserIdResult handleRequest(final GetTicketsAssignedToUserIdRequest getTicketsAssignedToUserIdRequest,
                                                          Context context) {
        return getApp().provideGetTicketsAssignedToUserActivity().handleRequest(getTicketsAssignedToUserIdRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
