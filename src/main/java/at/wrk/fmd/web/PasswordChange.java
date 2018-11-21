package at.wrk.fmd.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import at.wrk.fmd.service.PasswordService;

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
    public String updateOldPassword(@ModelAttribute UserCreationDto userTableSettings,
            @RequestParam("radiobutton") String radiobuttonName, BindingResult result, Model model, Errors errors) {
        if (errors.hasErrors()) {
            logger.error("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
            return "error";
        }

        passwordService.updatePassword(userTableSettings, radiobuttonName);
        
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "redirect:/passwordaenderung?success";
    }
}