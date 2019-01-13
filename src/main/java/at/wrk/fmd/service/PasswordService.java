package at.wrk.fmd.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import at.wrk.fmd.dto.UserCreationDto;
import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.pojo.CustomSort;
import at.wrk.fmd.pojo.User;
import at.wrk.fmd.repository.UserRepository;
import at.wrk.fmd.repository.updateOldPasswordRepository;

@Service
public class PasswordService extends CustomSort{

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private updateOldPasswordRepository passwordRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    public void updatePassword(UserCreationDto userTableSettings, String radiobuttonName) {
        
        List<User> users = userTableSettings.getUsers();
        
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername() == null && users.get(i).getPassword() != null) {
                String username = radiobuttonName;
                String newPassword = passwordEncoder.encode(users.get(i).getPassword());
                passwordRepo.updatePassword(username, newPassword);
            }
        }
    }
    
    public UserCreationDto showPasswordChangeForm() {
        UserCreationDto userForm = new UserCreationDto();
        User user;
        List<Benutzer> users;
        String authority = null;
        boolean isAdmin;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            authority = grantedAuthority.getAuthority();
        }
        isAdmin = authority.contains("ADMIN");
        
        if(isAdmin) {
            users = userRepo.findAllByOrderByIdAsc();
            for (int i = 0; i < users.size(); i++) {
                user = new User();
                user.setUsername(users.get(i).getBenutzername());
                user.setPassword(users.get(i).getPasswort());
                userForm.addUser(user);
            }
        } else { // User is Supervisor as well
            String username = authentication.getName();
            Benutzer singleUser = userRepo.findByBenutzername(username);
            user = new User();
            user.setUsername(singleUser.getBenutzername());
            user.setPassword(singleUser.getPasswort());
            userForm.addUser(user);
        }
        return userForm;
    }
}