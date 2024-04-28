package com.ticketsystem.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ticketsystem.service.dependency.App;
import com.ticketsystem.service.models.requests.UpdateTicketRequest;
import com.ticketsystem.service.models.results.UpdateTicketResult;

public class UpdateTicketActivityProvider implements RequestHandler<UpdateTicketRequest, UpdateTicketResult> {
    private static App app;

    public UpdateTicketActivityProvider() {

    }

    @Override
    public UpdateTicketResult handleRequest(final UpdateTicketRequest updateTicketRequest, Context context) {
        return getApp().provideUpdateTicketActivity().handleRequest(updateTicketRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }


}
