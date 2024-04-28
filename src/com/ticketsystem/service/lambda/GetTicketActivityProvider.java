package com.ticketsystem.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.dependency.App;
import com.ticketsystem.service.models.requests.GetTicketRequest;
import com.ticketsystem.service.models.results.GetTicketResult;

public class GetTicketActivityProvider implements RequestHandler<GetTicketRequest, GetTicketResult> {
    private static App app;

    public GetTicketActivityProvider() {

    }

    @Override
    public GetTicketResult handleRequest(final GetTicketRequest getTicketRequest, Context context) {
        return getApp().provideGetTicketActivity().handleRequest(getTicketRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }


}

