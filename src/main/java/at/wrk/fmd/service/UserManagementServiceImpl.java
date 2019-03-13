package at.wrk.fmd.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.wrk.fmd.dto.UserCreationDto;
import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.pojo.User;
import at.wrk.fmd.repository.UserManagementService;
import at.wrk.fmd.repository.UserRepository;
import at.wrk.fmd.repository.updateOldPasswordRepository;

@Service("userManagementServiceImpl")
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    UserRepository userRepo;
    
    @Override
    public List<Benutzer> getAllUsers() {
        return userRepo.findAllByOrderByIdAsc() ;
    }

    @Override
    public UserCreationDto saveUserForm(List<Benutzer> users) {
        UserCreationDto userForm = new UserCreationDto();
        for (int i = 0; i < users.size(); i++) {
            User user = new User();
            user.setUsername(users.get(i).getBenutzername());
            user.setAnzeigename(users.get(i).getAnzeigename());
            user.setDienstnummer(users.get(i).getDienstnummer());
            user.setIsActive(users.get(i).isActive());
            user.setPassword(users.get(i).getPasswort());
            user.setId(users.get(i).getId());
            userForm.addUser(user);
        }
        return userForm;
    }

    @Override
    public void updateActivePassiveUser(Boolean active, long id) {
        userRepo.setUserActive(active, id);
    }
}