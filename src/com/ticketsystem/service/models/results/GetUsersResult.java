package com.ticketsystem.service.models.results;

import com.ticketsystem.service.models.UserModel;

import java.util.List;

public class GetUsersResult {
    private List<UserModel> userList;

    public GetUsersResult(Builder builder) {
        this.userList = builder.userList;
    }

    public List<UserModel> getUserList() {
        return userList;
    }

    public void setUserList(List<UserModel> userList) {
        this.userList = userList;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private List<UserModel> userList;

        public Builder withUserList(List<UserModel> userListToUse) {
            this.userList = userListToUse;
            return this;
        }

        public GetUsersResult build() {return new GetUsersResult(this);}
    }


}
