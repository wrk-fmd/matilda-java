package at.wrk.fmd.matilda.dto;

import at.wrk.fmd.matilda.pojo.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

// TODO SMELL: This is a DTO for the UI. It should be possible to generate it in an immutable way,
//  in order to reduce possible errors of changes after retrieving.
public class UserCreationDto {

    @Valid
    private List<User> users;

    public UserCreationDto() {
        this.users = new ArrayList<>();
    }

    public UserCreationDto(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}