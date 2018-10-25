package at.wrk.fmd.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.wrk.fmd.dto.UserCreationDto;
import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.pojo.User;
import at.wrk.fmd.repository.UserRepository;
import at.wrk.fmd.repository.updateOldPasswordRepository;
import java.util.Collection;
import java.util.Iterator;

@Controller
@RequestMapping("/passwordaenderung")
public class PasswordChange {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private updateOldPasswordRepository passwordRepo;

    @GetMapping
    public String showPasswordChangeForm(Model model) {
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
        isAdmin = authorities.contains("ADMIN");
        
        if(isAdmin) {
            users = userRepo.findAll();
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
        model.addAttribute("UserCreationDto", userForm);
        return "passwordaenderung";
    }

    @PostMapping
    public String updateOldPassword(@ModelAttribute UserCreationDto userTableSettings,
            @RequestParam("radiobutton") String radiobutton, BindingResult result, Model model, Errors errors) {
        if (errors.hasErrors()) {
            return "error";
        }

        List<User> users = userTableSettings.getUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(radiobutton)) {
                String username = users.get(i).getUsername();
                String newPassword = passwordEncoder.encode(users.get(i).getPassword());
                passwordRepo.updatePassword(username, newPassword);
            }
        }
        return "redirect:/passwordaenderung?success";
    }
}