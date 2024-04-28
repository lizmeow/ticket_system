package com.ticketsystem.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.dependency.App;
import com.ticketsystem.service.models.requests.CreateTicketRequest;
import com.ticketsystem.service.models.results.CreateTicketResult;

public class CreateTicketActivityProvider implements RequestHandler<CreateTicketRequest, CreateTicketResult>  {
    private static App app;

    public CreateTicketActivityProvider() {

    }

    @Override
    public CreateTicketResult handleRequest(final CreateTicketRequest createTicketRequest, Context context) {
        return getApp().provideCreateTicketActivity().handleRequest(createTicketRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
