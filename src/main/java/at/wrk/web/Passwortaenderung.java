package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import at.wrk.model.Benutzer;
import at.wrk.pojo.User;
import at.wrk.repository.UserRepository;
import at.wrk.repository.updateOldPasswordRepository;
import at.wrk.web.dto.UserCreationDto;

@Controller
@RequestMapping("/passwordaenderung")
public class Passwortaenderung {
	
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
        
        List<Benutzer> b = userRepo.findAll();
        
        for(int i = 0; i<b.size(); i++) {
        	user = new User();
        	user.setUsername(b.get(i).getBenutzername());
        	user.setPassword(b.get(i).getPasswort());
           	userForm.addUser(user);
        }
        model.addAttribute("form", userForm);
        return "passwordaenderung";
    }
   
    @PostMapping
    public String updateOldPassword(@ModelAttribute UserCreationDto userTableSettings,
    								@RequestParam(value = "checkboxName", required = false) String checkboxValue,
    								BindingResult result,
    								Model model,
    								Errors errors) {
        if (errors.hasErrors()) {
            return "error";
        }

    	List<User> users = userTableSettings.getUsers();
    	String isChecked = checkboxValue;

    	if(users.size()==0) {
    		return "redirect:/passwordaenderung?stale";
    	}
    	
    	for(int i = 0; i<users.size(); i++) {
    		if(isChecked != null) {
    			if(isChecked.equals("on")) {
	    			String username = users.get(i).getUsername();
	    			String newPassword = passwordEncoder.encode(users.get(i).getPassword());
	    			passwordRepo.updatePassword(username, newPassword);
    			}
    		} else {
				return "redirect:/passwordaenderung?stale";
			}
    	}
    	return "redirect:/passwordaenderung?success";
    }
}