package at.wrk.fmd.matilda.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.wrk.fmd.matilda.dto.UserCreationDto;
import at.wrk.fmd.matilda.service.PasswordService;

@Controller
@RequestMapping("/passwordaenderung")
public class PasswordChange {

    @Autowired
    PasswordService passwordService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping
    public String showPasswordChangeForm(Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());

        UserCreationDto userForm = passwordService.showPasswordChangeForm();
        
        model.addAttribute("UserCreationDto", userForm);
        return "passwordaenderung";
    }

    @PostMapping
    public String updateOldPassword(@ModelAttribute @Valid UserCreationDto userTableSettings,
    		@RequestParam("radiobutton") String radiobuttonName, 
    		BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.error("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
            return "error";
        }

        for(int i = 0; i<userTableSettings.getUsers().size(); i++) {
        	if(userTableSettings.getUsers().get(i).getUsername().equals(radiobuttonName))
        		passwordService.updatePassword(userTableSettings, radiobuttonName);
        }
        
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "redirect:/passwordaenderung?success";
    }
}