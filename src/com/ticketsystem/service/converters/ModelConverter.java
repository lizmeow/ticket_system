package com.ticketsystem.service.converters;

import com.ticketsystem.service.dynamodb.Ticket;
import com.ticketsystem.service.dynamodb.User;
import com.ticketsystem.service.models.TicketModel;
import com.ticketsystem.service.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    public UserModel toUserModel(User user) {
        return UserModel.builder()
                .withId(user.getId())
                .withName(user.getName())
                .build();

    }

    public TicketModel toTicketModel(Ticket ticket) {
        return TicketModel.builder()
                .withTicketId(ticket.getTicketId())
                .withShortDescription(ticket.getShortDescription())
                .withLongDescription(ticket.getLongDescription())
                .withState(ticket.getState())
                .withCreationDate(ticket.getCreationDate())
                .withAssignedToUserId(ticket.getAssignedToUserId())
                .withCreatedByUserId(ticket.getCreatedByUserId())
                .withResolutionText(ticket.getResolutionText())
                .build();
    }

    public List<TicketModel> toTicketModelList(List<Ticket> tickets) {
        List<TicketModel> ret = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ret.add(toTicketModel(ticket));
        }
        return ret;
    }
}
