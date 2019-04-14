package at.wrk.fmd.matilda.service;

import at.wrk.fmd.matilda.dto.UserCreationDto;
import at.wrk.fmd.matilda.model.Benutzer;
import at.wrk.fmd.matilda.pojo.User;
import at.wrk.fmd.matilda.repository.UserManagementService;
import at.wrk.fmd.matilda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userManagementServiceImpl")
public class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;

    @Autowired
    public UserManagementServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Benutzer> getAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public UserCreationDto saveUserForm(final List<Benutzer> updatedUsers) {
        UserCreationDto userForm = new UserCreationDto();
        for (Benutzer updatedUser : updatedUsers) {
            User user = new User();
            user.setUsername(updatedUser.getBenutzername());
            user.setAnzeigename(updatedUser.getAnzeigename());
            user.setDienstnummer(updatedUser.getDienstnummer());
            user.setIsActive(updatedUser.isActive());
            user.setPassword(updatedUser.getPasswort());
            user.setId(updatedUser.getId());
            userForm.addUser(user);
        }

        return userForm;
    }

    @Override
    public void updateActivePassiveUser(Boolean active, String username, String anzeigename, String dienstnummer, long id) {
        userRepository.setUserActive(active, username, anzeigename, dienstnummer, id);
    }
}