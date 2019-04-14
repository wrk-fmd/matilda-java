package at.wrk.fmd.matilda.web;

import java.util.ArrayList;
import java.util.List;

import at.wrk.fmd.matilda.pojo.User;

public class UserCreation {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}