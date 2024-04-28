package com.ticketsystem.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.dependency.App;
import com.ticketsystem.service.models.requests.GetTicketsCreatedByUserRequest;
import com.ticketsystem.service.models.results.GetTicketsCreatedByUserResult;


public class GetTicketsCreatedByUserActivityProvider implements RequestHandler<GetTicketsCreatedByUserRequest, GetTicketsCreatedByUserResult> {
    private static App app;

    public GetTicketsCreatedByUserActivityProvider() {

    }

    @Override
    public GetTicketsCreatedByUserResult handleRequest(final GetTicketsCreatedByUserRequest getTicketsCreatedByUserRequest, Context context) {
        return getApp().provideGetTicketsCreatedByUserActivity().handleRequest(getTicketsCreatedByUserRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
