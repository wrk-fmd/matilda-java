package at.wrk.fmd.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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

        List<Benutzer> users = userRepo.findAll();

        for (int i = 0; i < users.size(); i++) {
            user = new User();
            user.setUsername(users.get(i).getBenutzername());
            user.setPassword(users.get(i).getPasswort());
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